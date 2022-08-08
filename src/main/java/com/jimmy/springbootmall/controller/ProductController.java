package com.jimmy.springbootmall.controller;

import com.jimmy.springbootmall.constant.ProductCategory;
import com.jimmy.springbootmall.dto.ProductQueryParams;
import com.jimmy.springbootmall.dto.ProductRequest;
import com.jimmy.springbootmall.model.Product;
import com.jimmy.springbootmall.service.ProductServcie;
import com.jimmy.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated  //@Max @Min 才能生效
@RestController
public class ProductController {
    @Autowired
    private ProductServcie productServcie;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(required = false) ProductCategory category , @RequestParam(required = false) String search,  //查詢條件Filtering
                                                     //排序sorting
                                                     @RequestParam (defaultValue = "created_date") String orderBy , @RequestParam(defaultValue = "desc") String sort,
                                                     //分頁 Pagination
                                                     @RequestParam(defaultValue = "5") @Max(1000)@Min(0) Integer limit, @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //取得product list
        List<Product> productsList = productServcie.getProducts(productQueryParams);
        //取得product 總數
        Integer total = productServcie.countProduct(productQueryParams);
        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productsList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
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
