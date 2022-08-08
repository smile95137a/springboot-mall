package com.jimmy.springbootmall.dao;

import com.jimmy.springbootmall.constant.ProductCategory;
import com.jimmy.springbootmall.dto.ProductQueryParams;
import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId , ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
