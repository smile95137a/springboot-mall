package com.jimmy.springbootmall.model;

public class OrderItem {
    private Integer orderItem;
    private Integer oderId;
    private Integer productId;
    private Integer quantity;
    private Integer amount;

    public Integer getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Integer orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getOderId() {
        return oderId;
    }

    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
