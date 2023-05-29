package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Shop;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Cashier implements Runnable{
    private final int id;
    private final Shop shop;

    public Cashier(int id, Shop shop) {
        this.id = id;
        this.shop = shop;
    }

    @Override
    public void run() {
        while (shop.isOpen()){
            try {
                Customer customer = shop.getCheckoutQueue().poll(1, TimeUnit.SECONDS);

                if (Objects.nonNull(customer)) {
                    checkoutCustomer(customer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkoutCustomer(Customer customer) {
        System.out.println(this.getName() + " is Checking out " + customer.getName());

        double total = 0;

        for (Item cartItem : customer.getCart()) {
            System.out.println(this.getName() + " added " + cartItem.getQuantity() + " " + cartItem.getProduct().getName() + " to " + customer.getName() + "'s checkout cart");

            try {
                cartItem.getProduct().purchase(cartItem.getQuantity());
                total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            } catch (IllegalArgumentException e) {
                System.out.println("Not enough stock for " + cartItem.getProduct().getName());
                shop.addProductToRestockQueue(cartItem.getProduct());
                System.out.println(this.getName() + " added " + cartItem.getProduct().getName() + " to the restock queue");
            }
        }

        System.out.println("Done checking out " + customer.getName() + " Total: Rs." + total);
    }

    public String getName() {
        return "Cashier-" + id;
    }
}
