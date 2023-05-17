package org.binara.sachin.dto.products;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;

public class IceCream extends Product {

    public IceCream() {
        super("IceCream", 250.0f, Constants.INITIAL_STOCK_AMOUNT);
    }
}
