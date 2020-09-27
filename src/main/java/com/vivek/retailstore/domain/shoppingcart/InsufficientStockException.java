package com.vivek.retailstore.domain.shoppingcart;

/**
 * The Class InsufficientStockException.
 */
public class InsufficientStockException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new insufficient stock exception.
	 *
	 * @param message the message
	 */
	public InsufficientStockException(String message) {
		super(message);
	}
}
