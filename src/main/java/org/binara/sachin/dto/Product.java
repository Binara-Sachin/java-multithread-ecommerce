package org.binara.sachin.dto;

import org.binara.sachin.constants.Constants;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Product {
    private final String name;
    private final double price;
    private int stockAmount;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.stockAmount = Constants.INITIAL_STOCK_AMOUNT;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        readWriteLock.readLock().lock();

        try {
            return stockAmount;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void addStock(int amount){
        readWriteLock.writeLock().lock();

        try {
            stockAmount += amount;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void purchase(int amount){
        readWriteLock.writeLock().lock();

        try {
            if (amount > stockAmount){
                throw new IllegalArgumentException("Stock amount cannot be less than zero");
            }
            stockAmount -= amount;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
