package com.qfedu.myshop.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用Druid数据库完备JDBC工具类
 * 1. JDBC数据库连接所需的必要资源准备和驱动加载
 * 2. 提供一个工具类方法，获取数据库连接对象 Connection getConnection
 * 3. 关闭用户使用的数据库资源
 *
 * @author Anonymous
 */
public class JDBCUtil {

    private static DataSource dataSource = null;

    static {
        Properties properties = new Properties();
        try {
            // 读取Properties配置文件
            properties.load(com.qfedu.myshop.utils.JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties"));

            // Druid数据库连接池工厂类创建一个数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回当前JDBCUtils创建的DataSource数据库连接池对象
     *
     * @return DataSource 数据库连接池对象
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取数据库连接对象方法
     *
     * @return java.sql.Connection 对象。如果获取连接失败返回null
     */
    public static Connection getConnection() {

        Connection connection = null;
        try {
            // 获取数据库连接从 dataSource 数据库连接池对象中获取对应的数据库连接对象。
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 数据库操作使用的资源都是AutoCloseable接口的实现类，可以使用
     * 不定长参数直接完成关闭方法，传入的参数是AutoCloseable实现类
     * 个数不限
     *
     * @param res AutoCloseable实现类对象，个数不限
     */
    public static void close(AutoCloseable... res) {
        try {
            // 遍历当前AutoCloseable数组，使用增强for循环
            for (AutoCloseable re : res) {
                // 当前资源不为null，直接关闭
                if (re != null) {
                    re.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

