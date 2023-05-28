package org.binara.sachin.dto;

import org.binara.sachin.dto.people.Cashier;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.dto.people.Manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Shop {
    private final ArrayList<Product> productList;
    private final ArrayList<Thread> cashierList;
    private final ArrayList<Thread> managerList;
    private final Set<Product> restockQueue; // A Set was used to avoid duplicate entries
    private final BlockingQueue<Customer> checkoutQueue;
    private final BlockingQueue<Item> newStockQueue;
    private int noOfShipments;
    private boolean isOpen;

    public Shop(int noOfCashiers, int noOfManagers) {
        isOpen = false;

        productList = new ArrayList<>();

        productList.add(new Product("Bread", 1.5));
        productList.add(new Product("Milk", 2.5));
        productList.add(new Product("Biscuit", 0.5));
        productList.add(new Product("Chocolate", 1.0));
        productList.add(new Product("Butter", 3.0));
        productList.add(new Product("Cheese", 2.0));
        productList.add(new Product("Egg", 0.5));
        productList.add(new Product("Yogurt", 1.5));
        productList.add(new Product("Ice Cream", 2.5));
        productList.add(new Product("Cake", 3.0));
        productList.add(new Product("Apple", 1.0));
        productList.add(new Product("Orange", 1.0));
        productList.add(new Product("Banana", 1.0));
        productList.add(new Product("Pineapple", 2.0));
        productList.add(new Product("Mango", 2.0));
        productList.add(new Product("Papaya", 2.0));
        productList.add(new Product("Pumpkin", 1.0));
        productList.add(new Product("Potato", 1.0));
        productList.add(new Product("Tomato", 1.0));
        productList.add(new Product("Onion", 1.0));

        cashierList = new ArrayList<>();
        for (int i = 0; i < noOfCashiers; i++) {
            cashierList.add(new Thread(new Cashier(i,this)));
        }

        managerList = new ArrayList<>();
        for (int i = 0; i < noOfManagers; i++) {
            managerList.add(new Thread(new Manager(i,this)));
        }

        checkoutQueue = new LinkedBlockingQueue<>();
        newStockQueue = new LinkedBlockingQueue<>();
        restockQueue = new HashSet<>();

        noOfShipments = 0;
    }

    public void openShop() {
        System.out.println("Shop is open");
        isOpen = true;

        //Initialize cashier threads
        for (Thread thread : cashierList) {
            thread.setPriority(7);
            thread.start();
        }

        //Initialize manager threads
        for (Thread thread : managerList) {
            thread.setPriority(10);
            thread.start();
        }
    }

    public void closeShop() {
        isOpen = false;

        try {
            for (Thread thread : cashierList) {
                thread.join();
            }
            for (Thread thread : managerList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Shop is closed");
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public BlockingQueue<Customer> getCheckoutQueue() {
        return checkoutQueue;
    }

    public void moveToCheckoutQueue(Customer customer){
        checkoutQueue.add(customer);
    }

    public int getCustomersInShop(){
        return checkoutQueue.size();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Set<Product> getRestockQueue() {
        return restockQueue;
    }

    public void addProductToRestockQueue(Product product){
        restockQueue.add(product);
        System.out.println(product.getName() + " added to restock queue");
    }

    public BlockingQueue<Item> getNewStockQueue() {
        return newStockQueue;
    }

    public void addProductToNewStock(Item item){
        newStockQueue.add(item);
    }

    public int recordNewShipment(){
        return ++noOfShipments;
    }
}
