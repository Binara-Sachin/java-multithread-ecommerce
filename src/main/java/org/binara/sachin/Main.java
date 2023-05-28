package org.binara.sachin;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;
import org.binara.sachin.dto.Shop;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.util.Util;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(Constants.NUMBER_OF_CASHIERS,Constants.NUMBER_OF_MANAGERS); // No of cashiers and managers can be changed in the constants class
        shop.openShop();

        int noOfCustomers = Constants.NUMBER_OF_CUSTOMERS; // No of customers can be changed in the constants class
        ArrayList<Thread> customerThreads = new ArrayList<>();

        for (int i = 0; i < noOfCustomers; i++) {
            customerThreads.add(new Thread(new Customer(i, shop)));
            customerThreads.get(i).start();
            Util.sleepRandomTime(1000);
        }

        for (int i = 0; i < noOfCustomers; i++) {
            try {
                customerThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (shop.getCustomersInShop() > 0){
            Util.sleep(1000);
        }

        shop.closeShop();
    }
}