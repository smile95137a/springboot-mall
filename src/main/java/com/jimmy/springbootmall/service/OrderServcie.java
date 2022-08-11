package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.dto.CreateOrderRequest;

public interface OrderServcie {

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
