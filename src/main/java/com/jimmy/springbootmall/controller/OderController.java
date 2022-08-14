package com.jimmy.springbootmall.controller;

import com.jimmy.springbootmall.dto.CreateOrderRequest;
import com.jimmy.springbootmall.dto.OrderQueryParams;
import com.jimmy.springbootmall.model.Order;
import com.jimmy.springbootmall.service.OrderServcie;
import com.jimmy.springbootmall.util.Page;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
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

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue =  "10") @Max(1000) Integer limit,
                                                 @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //取得order LIst
        List<Order> orderList = orderServcie.getOrders(orderQueryParams);

        //取得order總數
        Integer count = orderServcie.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}

