package com.jimmy.springbootmall.service.impl;

import com.jimmy.springbootmall.dao.OrderDao;
import com.jimmy.springbootmall.dao.ProductDao;
import com.jimmy.springbootmall.dto.BuyItem;
import com.jimmy.springbootmall.dto.CreateOrderRequest;
import com.jimmy.springbootmall.model.Order;
import com.jimmy.springbootmall.model.OrderItem;
import com.jimmy.springbootmall.model.Product;
import com.jimmy.springbootmall.service.OrderServcie;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderServcie {

        @Autowired
        private OrderDao orderDao;

        @Autowired
        private ProductDao productDao;

        @Override
        public Order getOrderById(Integer orderId) {
                Order order =  orderDao.getOrderById(orderId);

                List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

                order.setOrderItemList(orderItemList);

                return order;
        }

        @Transactional
        @Override
        public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

                int totalAmount = 0;
                List<OrderItem> orderItemList = new ArrayList<>();
                for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
                        Product product = productDao.getProductById(buyItem.getProductId());

                        //計算價錢
                        int amount = buyItem.getQuantity() * product.getPrice();
                        totalAmount = totalAmount + amount;

                        //轉換BuyItem to OrderItem
                        OrderItem orderItem = new OrderItem();
                        orderItem.setProductId(buyItem.getProductId());
                        orderItem.setQuantity(buyItem.getQuantity());
                        orderItem.setAmount(amount);

                        orderItemList.add(orderItem);
                }

                //創建訂單
                Integer orderId = orderDao.createOrder(userId , totalAmount);

                orderDao.createOrderItems(orderId,orderItemList);

                return orderId;
        }
}
