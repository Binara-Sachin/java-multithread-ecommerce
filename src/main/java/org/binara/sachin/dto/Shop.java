package org.binara.sachin.dto;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.people.Cashier;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.dto.people.Manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private final ArrayList<Product> productList;
    private final ArrayList<Thread> cashierList;
    private final ArrayList<Thread> managerList;
    private final ArrayList<Thread> shipmentList;
    private final Set<Product> restockQueue; // A Set was used to avoid duplicate entries
    ReentrantLock restockQueueLock = new ReentrantLock();
    private final BlockingQueue<Customer> checkoutQueue;
    private final BlockingQueue<Item> newStockQueue;
    private final ArrayList<Double> salesList;
    private int noOfShipments;
    private boolean isOpen;

    public Shop(int noOfCashiers, int noOfManagers) {
        isOpen = false;

        productList = new ArrayList<>();

        productList.add(new Product("Bread", 120.0));
        productList.add(new Product("Milk", 300.0));
        productList.add(new Product("Biscuit", 180.0));
        productList.add(new Product("Chocolate", 250.0));
        productList.add(new Product("Butter", 350.0));
        productList.add(new Product("Cheese", 580.0));
        productList.add(new Product("Egg", 30.0));
        productList.add(new Product("Yogurt", 60.0));
        productList.add(new Product("Ice Cream", 800.0));
        productList.add(new Product("Cake", 1200.0));
        productList.add(new Product("Apple", 120.0));
        productList.add(new Product("Orange", 100.0));
        productList.add(new Product("Banana", 350.0));
        productList.add(new Product("Pineapple", 200.0));
        productList.add(new Product("Mango", 60.0));
        productList.add(new Product("Papaya", 180.0));
        productList.add(new Product("Pumpkin", 150.0));
        productList.add(new Product("Potato", 100.0));
        productList.add(new Product("Tomato", 150.0));
        productList.add(new Product("Onion", 180.0));

        cashierList = new ArrayList<>();
        for (int i = 0; i < noOfCashiers; i++) {
            cashierList.add(new Thread(new Cashier(i, this)));
        }

        managerList = new ArrayList<>();
        for (int i = 0; i < noOfManagers; i++) {
            managerList.add(new Thread(new Manager(i, this)));
        }

        shipmentList = new ArrayList<>();
        checkoutQueue = new LinkedBlockingQueue<>();
        newStockQueue = new LinkedBlockingQueue<>();
        restockQueue = new HashSet<>();
        salesList = new ArrayList<>();

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
            for (Thread thread : shipmentList) {
                thread.join();
            }
            for (Thread thread : managerList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Shop is closed");
        printSummary();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public BlockingQueue<Customer> getCheckoutQueue() {
        return checkoutQueue;
    }

    public void moveToCheckoutQueue(Customer customer) {
        checkoutQueue.add(customer);
    }

    public int getCustomersInShop() {
        return checkoutQueue.size();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public synchronized void addProductToRestockQueue(Product product) {
        restockQueueLock.lock();
        try {
            restockQueue.add(product);
        } finally {
            restockQueueLock.unlock();
        }
    }

    public synchronized void requestNewShipmentIfNeeded(Manager manager) {
        restockQueueLock.lock();
        try {
            if (restockQueue.size() > 5) {
                System.out.println(manager.getName() + " is requesting new shipment");

                ArrayList<Product> restockNeededProducts = new ArrayList<>(restockQueue);
                restockQueue.clear();

                Thread shipment = new Thread(new Shipment(++noOfShipments, this, restockNeededProducts));
                shipment.setPriority(10);
                shipmentList.add(shipment);
                shipment.start();

                System.out.println(manager.getName() + " is done requesting new shipment");
            }
        } finally {
            restockQueueLock.unlock();
        }
    }

    public void addProductToNewStock(Item item) {
        newStockQueue.add(item);
    }

    public synchronized void addNewStocksToShop(Manager manager) {
        if (newStockQueue.size() > 0) {
            Item item = newStockQueue.poll();

            if (item != null) {
                Product product = item.getProduct();
                product.addStock(item.getQuantity());

                System.out.println(manager.getName() + " restocked " + product.getName() + " with " + item.getQuantity() + " items");
            }
        }
    }

    public void addSale(double sale) {
        salesList.add(sale);
    }

    private double calculateTotalSales() {
        double totalSales = 0;
        for (Double sale : salesList) {
            totalSales += sale;
        }
        return totalSales;
    }

    private void printSummary() {
        System.out.println("\n\n========================================");
        System.out.println("Total customers: " + Constants.NUMBER_OF_CUSTOMERS);
        System.out.println("Total sales: Rs." + calculateTotalSales());
        System.out.println("Total shipments: " + noOfShipments);
    }
}
