package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.dto.CreateOrderRequest;
import com.jimmy.springbootmall.model.Order;

public interface OrderServcie {
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
