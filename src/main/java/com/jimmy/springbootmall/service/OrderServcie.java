package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.dto.CreateOrderRequest;
import com.jimmy.springbootmall.dto.OrderQueryParams;
import com.jimmy.springbootmall.model.Order;

import java.util.List;

public interface OrderServcie {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
