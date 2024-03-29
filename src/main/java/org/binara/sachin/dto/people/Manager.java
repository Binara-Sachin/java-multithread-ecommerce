package org.binara.sachin.dto.people;

import org.binara.sachin.dto.Shop;
import org.binara.sachin.util.Util;

public class Manager implements Runnable {
    private final int id;
    private final Shop shop;

    public Manager(int id, Shop shop) {
        this.id = id;
        this.shop = shop;
    }

    @Override
    public void run() {
        while (shop.isOpen()) {
            shop.requestNewShipmentIfNeeded(this);
            shop.addNewStocksToShop(this);

            Util.sleep(1000);
        }
    }


    public String getName() {
        return "Manager-" + id;
    }
}
