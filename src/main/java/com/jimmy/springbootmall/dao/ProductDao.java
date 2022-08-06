package com.jimmy.springbootmall.dao;

import com.jimmy.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
