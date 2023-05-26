package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Product;
import org.binara.sachin.dto.Shipment;
import org.binara.sachin.dto.Shop;
import org.binara.sachin.util.Util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Manager implements Runnable{
    private final int id;
    private final Shop shop;

    public Manager(int id, Shop shop) {
        this.id = id;
        this.shop = shop;
    }

    @Override
    public void run() {
        while (shop.isOpen()){
            if (restockNeeded()) {
                requestNewShipment();
                continue;
            }

            if (shipmentAvailable()){
                restockProduct();
                continue;
            }

            Util.sleep(1000);
        }
    }

    public void requestNewShipment() {
        System.out.println(this.getName() + " is requesting new shipment");

        ArrayList<Product> restockNeededProducts = new ArrayList<>(shop.getRestockQueue());
        shop.getRestockQueue().clear();

        Thread shipment = new Thread(new Shipment(shop.recordNewShipment(), shop, restockNeededProducts));
        // Shipment threads will have the priority 10 inherited from the parent thread (A Manager thread)
        shipment.start();

        System.out.println(this.getName() + " is done requesting new shipment");
    }

    public boolean restockNeeded() {
        return shop.getRestockQueue().size() > 5;
    }

    public boolean shipmentAvailable() {
        return shop.getNewStockQueue().size() > 0;
    }

    public void restockProduct(){
        Item item = null;

        try {
            item = shop.getNewStockQueue().poll(1 , TimeUnit.SECONDS);
            assert item != null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AssertionError e) {
            System.out.println("No new stock available");
            return;
        }

        Product product = item.getProduct();
        product.addStock(item.getQuantity());

        System.out.println(this.getName() + " restocked " + product.getName() + " with " + item.getQuantity() + " items");
    }

    public String getName() {
        return "Manager-" + id;
    }
}
