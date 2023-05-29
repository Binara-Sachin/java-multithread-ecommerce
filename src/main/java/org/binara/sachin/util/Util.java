package org.binara.sachin.util;

import org.binara.sachin.dto.Item;
import org.binara.sachin.dto.Product;

import java.util.ArrayList;
import java.util.LinkedList;

public class Util {
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    public static int getRandomNumberLessThan(int max) {
        return (int) (Math.random() * (max - 1) + 1);
    }

    public static void sleepRandomTime(int maxTime) {
        sleep(getRandomNumberLessThan(maxTime));
    }

    //Get random product from the list
    public static Product getRandomProduct(ArrayList<Product> productList) {
        int randomIndex = getRandomNumberLessThan(productList.size());
        return productList.get(randomIndex);
    }

    //Check if the product is available in the list
    public static boolean productAlreadyInList(LinkedList<Item> shoppingList, Product product) {
        for (Item item : shoppingList) {
            if (item.getProduct() == product) {
                return true;
            }
        }
        return false;
    }
}
