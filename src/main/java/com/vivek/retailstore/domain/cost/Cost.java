package com.vivek.retailstore.domain.cost;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

import lombok.NonNull;

/**
 * Value object represents a Cost attributes associated with any purchased item.
 * It have fields representing costs such as Gross Price, Tax, Freight charges,
 * Discount, etc. It also have method which calculates Total Cost.
 */
@Embeddable
public class Cost {

	/** The gross amount. */
	@NonNull
	private BigDecimal grossAmount;

	/** The sales tax. */
	@NonNull
	private BigDecimal salesTax;

	/**
	 * Instantiates a new cost.
	 */
	public Cost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new cost.
	 *
	 * @param grossAmount the gross amount
	 * @param salesTax the sales tax
	 */
	public Cost(@NonNull BigDecimal grossAmount, @NonNull BigDecimal salesTax) {
		super();
		this.grossAmount = grossAmount;
		this.salesTax = salesTax;
	}

	/**
	 * Gets the gross amount.
	 *
	 * @return the gross amount
	 */
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	/**
	 * Sets the gross amount.
	 *
	 * @param grossAmount the new gross amount
	 */
	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * Gets the sales tax.
	 *
	 * @return the sales tax
	 */
	public BigDecimal getSalesTax() {
		return salesTax;
	}

	/**
	 * Sets the sales tax.
	 *
	 * @param salesTax the new sales tax
	 */
	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return grossAmount.add(salesTax);
	}

}
