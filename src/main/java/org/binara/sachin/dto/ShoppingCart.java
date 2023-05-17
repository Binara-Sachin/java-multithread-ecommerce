package org.binara.sachin.dto;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Product, Integer> products;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (this.products.containsKey(product)) {
            this.products.put(product, this.products.get(product) + quantity);
        } else {
            this.products.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (this.products.containsKey(product)) {
            this.products.put(product, this.products.get(product) - quantity);
        }
    }
}
