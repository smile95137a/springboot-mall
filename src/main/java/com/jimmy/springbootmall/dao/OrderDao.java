package com.jimmy.springbootmall.dao;

import com.jimmy.springbootmall.dto.OrderQueryParams;
import com.jimmy.springbootmall.model.Order;
import com.jimmy.springbootmall.model.OrderItem;
import com.jimmy.springbootmall.model.User;

import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemByOrderId(Integer orderId);
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItems(Integer orderId , List<OrderItem> orderItemList);
}
