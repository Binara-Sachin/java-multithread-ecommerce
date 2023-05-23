package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Product;

import java.util.LinkedList;

public class Customer implements Runnable{
    private LinkedList<Item> shoppingList;
    private LinkedList<Item> cart;


    public Customer() {
        shoppingList = new LinkedList<>();

        // Add items to shopping list
        // Generate random shopping list for convenience

        cart = new LinkedList<>();
    }

    @Override
    public void run() {
        goShopping();
    }

    public void goShopping(){
        while (shoppingList.size() > 0){
            Item item = shoppingList.getFirst();
            addProductToCart(item);
        }
    }

    public void addProductToCart(Item item) {
        cart.add(item);
        shoppingList.remove(item);
    }
}
