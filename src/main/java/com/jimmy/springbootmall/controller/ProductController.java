package com.jimmy.springbootmall.controller;

import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;
import com.jimmy.springbootmall.service.ProductServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private ProductServcie productServcie;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productServcie.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createPeoduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productServcie.createPeoduct(productRequest);

        Product product = productServcie.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
