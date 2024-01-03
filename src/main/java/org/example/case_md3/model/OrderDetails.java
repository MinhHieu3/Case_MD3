package org.example.case_md3.model;

public class OrderDetails {
    private Order idOrder;
    private Product idProduct;
    private int quantity;
    private double price;

    public OrderDetails() {
    }

    public OrderDetails(Order idOrder, Product idProduct) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
    }

    public OrderDetails(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetails(Order idOrder, Product idProduct, int quantity, double price) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
