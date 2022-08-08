package com.jimmy.springbootmall.controller;

import com.jimmy.springbootmall.constant.ProductCategory;
import com.jimmy.springbootmall.dto.ProductQueryParams;
import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;
import com.jimmy.springbootmall.service.ProductServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductServcie productServcie;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) ProductCategory category , @RequestParam(required = false) String search){

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);

        List<Product> productsList = productServcie.getProducts(productQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

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
        Integer productId = productServcie.createProduct(productRequest);

        Product product = productServcie.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId , @RequestBody @Valid ProductRequest productRequest){

        // 檢查product是否存在
        Product product = productServcie.getProductById(productId);

        if(product == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品的數據
        productServcie.updateProduct(productId,productRequest);

        Product updateProduct = productServcie.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productServcie.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
