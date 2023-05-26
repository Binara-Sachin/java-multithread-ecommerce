package org.binara.sachin;

import org.binara.sachin.dto.Shop;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.util.Util;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(5,2);
        shop.openShop();

        int noOfCustomers = 10;

        for (int i = 0; i < noOfCustomers; i++) {
            new Thread(new Customer(i, shop)).start();
            Util.sleepRandomTime(1000);
        }


    }
}