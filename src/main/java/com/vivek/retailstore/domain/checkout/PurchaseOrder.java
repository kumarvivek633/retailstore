package com.vivek.retailstore.domain.checkout;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vivek.retailstore.domain.cost.Cost;

/**
 * The Class PurchaseOrder.
 */
@Entity
public class PurchaseOrder {

	/** The purchase order id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long purchaseOrderId;

	/** The customer name. */
	private String customerName;

	/** The total price. */
	private Cost totalPrice;

	/** The order items. */
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

	/**
	 * Gets the purchase order id.
	 *
	 * @return the purchase order id
	 */
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * Sets the purchase order id.
	 *
	 * @param purchaseOrderId the new purchase order id
	 */
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public Cost getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price.
	 *
	 * @param totalPrice the new total price
	 */
	public void setTotalPrice(Cost totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the order items.
	 *
	 * @return the order items
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * Sets the order items.
	 *
	 * @param orderItems the new order items
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
