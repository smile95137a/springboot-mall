package com.jimmy.springbootmall.constant;

public class MyTest {
    public static void main(String[] args) {
        ProductCategory category = ProductCategory.FOOD;
        String s = category.name();
        System.out.println(s);
        String s2 = "CAR";
        ProductCategory productCategory2 = ProductCategory.valueOf(s2);

    }
}
