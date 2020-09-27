package com.vivek.retailstore.testdata;

import java.math.BigDecimal;

import com.vivek.retailstore.domain.cost.SalesTaxCategory;
import com.vivek.retailstore.domain.productcatalog.Product;

/**
 * The Class TestProduct.
 */
public class TestProduct {

	/** The Constant phone. */
	public static final Product phone = new Product("Samsung Galaxy S10", "001123444545", SalesTaxCategory.CATEGORY_A,
			new BigDecimal(5000.00));

	/** The Constant phoneCase. */
	public static final Product phoneCase = new Product("Samsung Galaxy S10", "4584125888",
			SalesTaxCategory.CATEGORY_B, new BigDecimal(20.00));
}
