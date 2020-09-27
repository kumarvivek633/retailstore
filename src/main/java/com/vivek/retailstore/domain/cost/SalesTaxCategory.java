package com.vivek.retailstore.domain.cost;

import java.math.BigDecimal;

import lombok.NonNull;

/**
 * The Enum SalesTaxCategory.
 */
public enum SalesTaxCategory {

	/** The category a. */
	CATEGORY_A("Category A", new BigDecimal(10)),
	/** The category b. */
	CATEGORY_B("Category B", new BigDecimal(20)),

	/** The category c. */
	CATEGORY_C("Category C", new BigDecimal(0));

	/** The category name. */
	@NonNull
	private String categoryName;

	/** The sales tax applicable. */
	@NonNull
	private BigDecimal salesTaxApplicable;

	/**
	 * Instantiates a new sales tax category.
	 *
	 * @param categoryName       the category name
	 * @param salesTaxApplicable the sales tax applicable
	 */
	private SalesTaxCategory(@NonNull String categoryName, @NonNull BigDecimal salesTaxApplicable) {
		this.categoryName = categoryName;
		this.salesTaxApplicable = salesTaxApplicable;
	}

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Gets the sales tax applicable.
	 *
	 * @return the sales tax applicable
	 */
	public BigDecimal getSalesTaxApplicable() {
		return salesTaxApplicable;
	}

}
