package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Product;
import org.binara.sachin.dto.Shop;
import org.binara.sachin.util.Util;

import java.util.LinkedList;

public class Customer implements Runnable{
    private final int id;
    private final Shop shop;
    private LinkedList<Item> shoppingList;
    private LinkedList<Item> cart;

    public Customer(int id, Shop shop) {
        this.id = id;
        this.shop = shop;

        shoppingList = new LinkedList<>();
        cart = new LinkedList<>();

        int numberOfCartItems = Util.getRandomNumberLessThan(shop.getProductList().size());

        for (int i = 0; i < numberOfCartItems; i++) {
            Product product = Util.getRandomProduct(shop.getProductList());
            int quantity = Util.getRandomNumberLessThan(product.getStockAmount());

            if(!Util.productAlreadyInList(shoppingList, product)){
                shoppingList.add(new Item(product, quantity));
            }
        }
    }

    @Override
    public void run() {
        goShopping();
    }

    public void goShopping(){
        System.out.println(this.getName() + " started shopping");

        while (shoppingList.size() > 0){
            Item item = shoppingList.getFirst();

            if(item.getProduct().getStockAmount() < item.getQuantity()){
                item.setQuantity(item.getProduct().getStockAmount());
            }

            addProductToCart(item);
            Util.sleepRandomTime(1000); //Assumption: Customer will take random amount of time to add products to cart
        }

        shop.moveToCheckoutQueue(this);
        System.out.println(this.getName() + " is done shopping");
        Thread.yield();
    }

    public void addProductToCart(Item item) {
        cart.add(item);
        shoppingList.remove(item);

        System.out.println(this.getName() + " added " + item.getQuantity() + " " + item.getProduct().getName() + " to cart");
    }

    public String getName() {
        return "Customer-" + id;
    }

    public LinkedList<Item> getCart() {
        return cart;
    }
}
