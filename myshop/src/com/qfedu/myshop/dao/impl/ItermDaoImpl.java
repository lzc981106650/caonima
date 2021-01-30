package com.qfedu.myshop.dao.impl;

import com.qfedu.myshop.dao.ItermDao;
import com.qfedu.myshop.entity.Cart;
import com.qfedu.myshop.entity.Item;
import com.qfedu.myshop.entity.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;
import utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItermDaoImpl extends BaseDao implements ItermDao {
    /**
     * iterm 表添加数据
     * @param carts
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public int addIterm(List<Cart> carts, String oid) throws SQLException {
        String sql = "insert into shoping2009.item(o_id, p_id, i_count, i_num) VALUES (?, ?, ?, ?)";
        if(carts != null) {
            for(Cart cart : carts) {
                queryRunner.update(sql, oid, cart.getPid(), cart.getCcount(), cart.getCnum());
            }
        }
        return 0;
    }

    /**
     * 列表详情
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Item> detail(String oid) throws SQLException {
        String sql = "select i_id iid, o_id oid,i.p_id pid, i_count icount, i_num inum,t_id tid,p_name pname,p_time ptime,p_image pimage,p_state pstate,p_info pinfo,p_price pprice from shoping2009.item i left join shoping2009.product p on i.p_id = p.p_id where o_id = ?";

        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), oid);

        if(query == null) {
            return null;
        }

        List<Item> items = new ArrayList<>();

        for(Map<String, Object> map : query) {
            Product product = new Product();
            Item item = new Item();

            try {
                BeanUtils.populate(product, map);
                BeanUtils.populate(item, map);
            } catch(IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            item.setProduct(product);
            items.add(item);
        }
        return items;
    }
}
