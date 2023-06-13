package org.binara.sachin;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;
import org.binara.sachin.dto.Shop;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.util.Util;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(Constants.NUMBER_OF_CASHIERS, Constants.NUMBER_OF_MANAGERS); // No of cashiers and managers can be changed in the constants class
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

        while (shop.getCustomersInShop() > 0) {
            Util.sleep(1000);
        }

        shop.closeShop();
    }
}

// Design and Assumptions are also available in Readme.md file
/*
    ## Assumptions

    ### Products
    - Product prices are final. They will not change after the product is created.
    - For convenience, products have an initial stock of 100.
    - Products are requested to be restocked by cashiers when the stock is less than 10.

    ### Customers
    - Customers have a list of shopping items (products and desired amounts) when they enter the shop.
    - If a product had less than the desired amount in stock, the customer will buy all the available stock
    - If a product is out of stock, customer will not buy the product
    - When checking out if the product is out of stock, the customer will not buy the product

    ### Shipments
    - Shipments will arrive after a fixed interval when a shipment request is made by a manager
    - All products will be restocked by 50 when a shipment arrives
*/