package org.binara.sachin.dto;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.util.Util;

import java.util.ArrayList;

public class Shipment implements Runnable{
    private final int id;
    private final Shop shop;
    private final ArrayList<Product> products;

    public Shipment(int id, Shop shop, ArrayList<Product> products) {
        this.id = id;
        this.shop = shop;
        this.products = products;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " is on the way");
        Util.sleep(Constants.SHIPMENT_ARRIVAL_INTERVAL);
        System.out.println(this.getName() + " has arrived");
        addProductsToShop();
    }

    private void addProductsToShop() {
        products.forEach(product -> {
            System.out.println("Adding " + product.getName() + " to new stock");
            Item item = new Item(product, Constants.RESTOCK_AMOUNT);
            shop.addProductToNewStock(item);
        });
    }

    public String getName() {
        return "Shipment-" + id;
    }
}
