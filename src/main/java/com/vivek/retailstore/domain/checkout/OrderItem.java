package com.vivek.retailstore.domain.checkout;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.vivek.retailstore.domain.cost.Cost;

import javax.persistence.Embedded;

/**
 * The Class OrderItem.
 */
@Entity
public class OrderItem {

	/** The order item id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderItemId;

	/** The product name. */
	private String productName;

	/** The order quantity. */
	private BigDecimal orderQuantity;

	/** The unit price. */
	private BigDecimal unitPrice;

	/** The total cost. */
	@Embedded
	private Cost totalCost;

	/**
	 * Gets the order item id.
	 *
	 * @return the order item id
	 */
	public Long getOrderItemId() {
		return orderItemId;
	}

	/**
	 * Sets the order item id.
	 *
	 * @param orderItemId the new order item id
	 */
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the order quantity.
	 *
	 * @return the order quantity
	 */
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * Sets the order quantity.
	 *
	 * @param orderQuantity the new order quantity
	 */
	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * Gets the unit price.
	 *
	 * @return the unit price
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Sets the unit price.
	 *
	 * @param unitPrice the new unit price
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the total cost.
	 *
	 * @return the total cost
	 */
	public Cost getTotalCost() {
		return totalCost;
	}

	/**
	 * Sets the total cost.
	 *
	 * @param totalCost the new total cost
	 */
	public void setTotalCost(Cost totalCost) {
		this.totalCost = totalCost;
	}

}
