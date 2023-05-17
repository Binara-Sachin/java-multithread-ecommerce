package org.binara.sachin.dto.products;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;

public class Apple extends Product {

    public Apple(int quantity) {
        super("Apple", 50.0f, Constants.INITIAL_STOCK_AMOUNT);
    }
}
