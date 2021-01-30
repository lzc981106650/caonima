package utils;

import com.qfedu.myshop.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseDao {
    public static QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());

    /**
     * 通用update更新方法，用于处理insert update delete SQL语句
     *
     * @param sql        当前需要指定的目标SQL语句
     * @param parameters 当前SQL语句对应的参数，为Object类型不定长参数
     * @return 当前SQL语句执行对于数据库的影响行数
     * @throws SQLException SQL异常
     */
    public int update(String sql, Object... parameters) throws SQLException {
        return queryRunner.update(sql, parameters);
    }

    /**
     * 查询一个符合JavaBean规范类对象
     *
     * @param sql        需要处理的SQL语句
     * @param cls        目标数据类型Class对象
     * @param parameters 对应当前SQL语句的参数
     * @param <T>        泛型约束当前数据类型，需要通过参数传入
     * @return 泛型约束数据类型
     * @throws SQLException SQL异常
     */
    public <T> T queryBean(String sql, Class<T> cls, Object... parameters) throws SQLException {
        return queryRunner.query(sql, new BeanHandler<>(cls), parameters);
    }

    /**
     * 通用query查询方法，用于处理select语句
     *
     * @param sql        Select SQL 语句 DQL语句
     * @param cls        当前查询目标的数据类型Class对象，
     * @param parameters 对应SQL语句参数
     * @param <T>        泛型约束当前数据类型，需要通过参数传入
     * @return List集合包含有用户查询的目标数据，如果为找到任何数据，返回null
     * @throws SQLException SQL异常
     */
    public <T> List<T> queryBeanList(String sql, Class<T> cls, Object... parameters)
            throws SQLException {
        return queryRunner.query(sql, new BeanListHandler<>(cls), parameters);
    }

    public Map<String, Object> queryMap(String sql, Object... parameters) throws SQLException {
        return queryRunner.query(sql, new MapHandler(), parameters);
    }

    public List<Map<String, Object>> queryMapList(String sql, Object... parameters) throws SQLException {
        return queryRunner.query(sql, new MapListHandler(), parameters);
    }

    public Object[] queryArray(String sql, Object... parameters) throws SQLException {
        return queryRunner.query(sql, new ArrayHandler(), parameters);
    }

    public List<Object[]> queryArrayList(String sql, Object... parameters) throws SQLException {
        return queryRunner.query(sql, new ArrayListHandler(), parameters);
    }
}
