package com.jimmy.springbootmall.controller;

import com.jimmy.springbootmall.dto.CreateOrderRequest;
import com.jimmy.springbootmall.model.Order;
import com.jimmy.springbootmall.service.OrderServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OderController {

    @Autowired
    private OrderServcie orderServcie;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderServcie.createOrder(userId,createOrderRequest);

        Order order = orderServcie.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


}

