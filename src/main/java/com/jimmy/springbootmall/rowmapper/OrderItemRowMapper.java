package com.jimmy.springbootmall.rowmapper;

import com.jimmy.springbootmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItem(resultSet.getInt("order_item_id"));
        orderItem.setOderId(resultSet.getInt("order_id"));
        orderItem.setProductId(resultSet.getInt("product_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setAmount(resultSet.getInt("amount"));


        orderItem.setProductName(resultSet.getString("product_id"));
        orderItem.setImageUrl(resultSet.getString("image_url"));

        return orderItem;

    }
}
