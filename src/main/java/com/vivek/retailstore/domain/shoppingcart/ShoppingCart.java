package com.vivek.retailstore.domain.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.NoArgsConstructor;

/**
 * The Class ShoppingCart.
 */
@NoArgsConstructor
@Entity
public class ShoppingCart {

	/**
	 * The Enum CartStatus.
	 */
	public static enum CartStatus {

		/** The created. */
		CREATED,
		/** The checkedout. */
		CHECKEDOUT
	}

	/**
	 * Instantiates a new shopping cart.
	 *
	 * @param customerName the customer name
	 */
	public ShoppingCart(String customerName) {
		this.customerName = customerName;
		cartStatus = CartStatus.CREATED;
	}

	/** The cart id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;

	/** The customer name. */
	private String customerName;

	/** The cart status. */
	@Enumerated(EnumType.STRING)
	private CartStatus cartStatus;

	/** The purchase order id. */
	private Long purchaseOrderId;

	/** The cart item list. */
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cartItemList = new ArrayList<>();

	/**
	 * Gets the cart id.
	 *
	 * @return the cart id
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * Sets the cart id.
	 *
	 * @param cartId the new cart id
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
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
	 * Gets the cart status.
	 *
	 * @return the cart status
	 */
	public CartStatus getCartStatus() {
		return cartStatus;
	}

	/**
	 * Sets the cart status.
	 *
	 * @param cartStatus the new cart status
	 */
	public void setCartStatus(CartStatus cartStatus) {
		this.cartStatus = cartStatus;
	}

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
	 * Gets the cart item list.
	 *
	 * @return the cart item list
	 */
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	/**
	 * Sets the cart item list.
	 *
	 * @param cartItemList the new cart item list
	 */
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

}
