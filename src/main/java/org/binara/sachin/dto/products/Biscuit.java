package org.binara.sachin.dto.products;

import org.binara.sachin.constants.Constants;
import org.binara.sachin.dto.Product;

public class Biscuit extends Product {

        public Biscuit() {
            super("Biscuit", 50.0f, Constants.INITIAL_STOCK_AMOUNT);
        }
}
