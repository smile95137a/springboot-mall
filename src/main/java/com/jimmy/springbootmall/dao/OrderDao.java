package com.jimmy.springbootmall.dao;

import com.jimmy.springbootmall.model.OrderItem;
import com.jimmy.springbootmall.model.User;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItems(Integer orderId , List<OrderItem> orderItemList);
}
