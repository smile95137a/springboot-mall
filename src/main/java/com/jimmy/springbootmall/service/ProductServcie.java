package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;

public interface ProductServcie {
    Product getProductById(Integer productId);
    Integer createPeoduct(ProductRequest productRequest);
}
