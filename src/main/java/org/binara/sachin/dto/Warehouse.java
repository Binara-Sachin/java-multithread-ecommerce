package org.binara.sachin.dto;

import org.binara.sachin.constants.Constants;

import java.util.LinkedList;

public class Warehouse {
    private LinkedList<Stock> stockList;

    public Warehouse(LinkedList<Product> productList) {
        stockList = new LinkedList<>();

        for (Product product : productList) {
            stockList.add(new Stock(product, Constants.INITIAL_STOCK_AMOUNT));
        }
    }

    public int getStock(Product product) {
        for (Stock stock : stockList) {
            if (stock.getProduct().equals(product)) {
                return stock.getQuantity();
            }
        }

        return 0;
    }
}
