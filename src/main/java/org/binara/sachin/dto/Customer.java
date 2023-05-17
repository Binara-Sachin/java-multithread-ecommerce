package org.binara.sachin.dto;

public class Customer {
    private ShoppingCart shoppingCart;

    public Customer() {
        this.shoppingCart = new ShoppingCart();
    }

    public void addProductToCart(Product product, int quantity) {
        this.shoppingCart.addProduct(product, quantity);
    }

    public void removeProductFromCart(Product product, int quantity) {
        this.shoppingCart.removeProduct(product, quantity);
    }
}
