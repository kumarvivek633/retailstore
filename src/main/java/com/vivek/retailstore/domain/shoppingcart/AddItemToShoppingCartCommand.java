package com.vivek.retailstore.domain.shoppingcart;

import java.math.BigDecimal;

import lombok.NonNull;

/**
 * The Class AddItemToShoppingCartCommand.
 */
public class AddItemToShoppingCartCommand {

	/** The shopping counter id. */
	@NonNull
	private Long shoppingCounterId;

	/** The shopping cart id. */
	@NonNull
	private Long shoppingCartId;

	/** The product id. */
	@NonNull
	private Long productId;

	/** The quantity. */
	@NonNull
	private BigDecimal quantity;
	
	/**
	 * Instantiates a new adds the item to shopping cart command.
	 */
	public AddItemToShoppingCartCommand() {
		super();
	}

	/**
	 * Instantiates a new adds the item to shopping cart command.
	 *
	 * @param shoppingCounterId the shopping counter id
	 * @param shoppingCartId the shopping cart id
	 * @param productId the product id
	 * @param quantity the quantity
	 */
	public AddItemToShoppingCartCommand(@NonNull Long shoppingCounterId, @NonNull Long shoppingCartId,
			@NonNull Long productId, @NonNull BigDecimal quantity) {
		super();
		this.shoppingCounterId = shoppingCounterId;
		this.shoppingCartId = shoppingCartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	/**
	 * Gets the shopping counter id.
	 *
	 * @return the shopping counter id
	 */
	public Long getShoppingCounterId() {
		return shoppingCounterId;
	}

	/**
	 * Gets the shopping cart id.
	 *
	 * @return the shopping cart id
	 */
	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

}
