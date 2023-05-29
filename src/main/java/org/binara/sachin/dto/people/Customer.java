package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Product;
import org.binara.sachin.dto.Shop;
import org.binara.sachin.util.Util;

import java.util.LinkedList;

public class Customer implements Runnable{
    private final int id;
    private final Shop shop;
    private final LinkedList<Item> shoppingList;
    private final LinkedList<Item> cart;

    public Customer(int id, Shop shop) {
        this.id = id;
        this.shop = shop;

        shoppingList = new LinkedList<>();
        cart = new LinkedList<>();

        int numberOfCartItems = Util.getRandomNumberLessThan(shop.getProductList().size());

        for (int i = 0; i < numberOfCartItems; i++) {
            Product product = Util.getRandomProduct(shop.getProductList());
            int quantity = Util.getRandomNumberLessThan(10);

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
            int amountInStock = item.getProduct().getStockAmount();

            if(amountInStock > 0){
                if(amountInStock < item.getQuantity()){
                    item.setQuantity(item.getProduct().getStockAmount());
                    System.out.println(this.getName() + " wants " + item.getProduct().getName() + ". But only " + item.getQuantity() + " units are available");
                }
                addProductToCart(item);
            } else {
                System.out.println(this.getName() + " wants " + item.getProduct().getName() + ". But item is out of stock");
                shoppingList.remove(item);
            }

            Util.sleepRandomTime(1000); //Assumption: Customer will take random amount of time to add products to cart
        }

        if (cart.size() > 0) {
            shop.moveToCheckoutQueue(this);
            System.out.println(this.getName() + " is done shopping and went to checkout queue");
        } else {
            System.out.println(this.getName() + " didn't find anything to buy");
        }
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
