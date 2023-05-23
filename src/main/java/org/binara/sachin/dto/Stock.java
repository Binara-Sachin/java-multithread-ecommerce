package org.binara.sachin.dto;

public class Stock {
    private final Product product;
    private int quantity;

    public Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void addStock(int amount) {
        this.quantity += amount;
    }

    public void removeStock(int amount) {
        this.quantity -= amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
