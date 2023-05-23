package org.binara.sachin.dto;

import org.binara.sachin.dto.people.Cashier;
import org.binara.sachin.dto.people.Customer;
import org.binara.sachin.dto.people.Manager;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Shop {
    private LinkedList<Product> productList;
    private final LinkedList<Cashier> cashierList;
    private final LinkedList<Manager> managerList;
    private Warehouse warehouse;
    private PriorityQueue<Customer> checkoutQueue;


    public Shop(int noOfCashiers, int noOfManagers) {
        productList = new LinkedList<>();

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

        cashierList = new LinkedList<>();
        for (int i = 0; i < noOfCashiers; i++) {
            cashierList.add(new Cashier());
        }

        managerList = new LinkedList<>();
        for (int i = 0; i < noOfManagers; i++) {
            managerList.add(new Manager());
        }

        warehouse = new Warehouse(productList);

        checkoutQueue = new PriorityQueue<>();
    }

    public void openShop() {
        System.out.println("Shop is open");

        //Initialize cashier threads
        for (Cashier cashier : cashierList) {
//            cashier.start();
        }

        //Initialize manager threads
        for (Manager manager : managerList) {
//            manager.start();
        }
    }

    public void closeShop() {
        //Stop cashier threads
        for (Cashier cashier : cashierList) {
//            cashier.stop();
        }

        //Stop manager threads
        for (Manager manager : managerList) {
//            manager.stop();
        }

        System.out.println("Shop is closed");
    }

    public LinkedList<Product> getProductList() {
        return productList;
    }

    public int getProductAvailability(Product product) {
        return warehouse.getStock(product);
    }

    public void getIntoToCheckoutQueue(Customer customer){
        checkoutQueue.add(customer);
    }
}
