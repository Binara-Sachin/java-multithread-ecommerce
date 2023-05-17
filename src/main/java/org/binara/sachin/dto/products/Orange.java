package org.binara.sachin.dto.products;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;

public class Orange extends Product {

    public Orange() {
        super("Orange", 60.0f, Constants.INITIAL_STOCK_AMOUNT);
    }
}
