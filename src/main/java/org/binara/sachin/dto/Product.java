package org.binara.sachin.dto;

public abstract class Product {
    private final String name;
    private final float price;
    private int quantity;

    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void purchase(int quantity){
        this.quantity -= quantity;
    }

    public void addStock(int quantity){
        this.quantity += quantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
