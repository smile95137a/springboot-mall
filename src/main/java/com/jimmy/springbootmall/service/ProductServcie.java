package com.jimmy.springbootmall.service;

import com.jimmy.springbootmall.constant.ProductCategory;
import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;

import java.util.List;

public interface ProductServcie {
    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId , ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
