package com.vivek.retailstore.domain.checkout;

import java.math.BigDecimal;

import com.vivek.retailstore.domain.cost.Cost;
import com.vivek.retailstore.domain.cost.SalesTaxCalculator;
import com.vivek.retailstore.domain.shoppingcart.CartItem;
import com.vivek.retailstore.storefront.util.BeanUtil;

/**
 * The Class OrderItemBuilder.
 */
public final class OrderItemBuilder {

	/** The cart item. */
	private CartItem cartItem;

	/** The sales tax calculator. */
	private SalesTaxCalculator salesTaxCalculator;

	/**
	 * Builds the.
	 *
	 * @return the order item
	 */
	public OrderItem build() {
		if (this.cartItem == null) {
			throw new IllegalArgumentException("Cart Item is mandatory");
		}

		if (this.salesTaxCalculator == null) {
			salesTaxCalculator = BeanUtil.getBean(SalesTaxCalculator.class);
		}

		final OrderItem orderItem = new OrderItem();
		orderItem.setProductName(this.cartItem.getProduct().getProductName());
		orderItem.setOrderQuantity(this.cartItem.getQuantity());
		orderItem.setUnitPrice(this.cartItem.getProduct().getUnitPrice());

		final BigDecimal totalSalesTax = this.salesTaxCalculator.calculateSalesTax(cartItem);

		final Cost itemCost = new Cost(this.cartItem.getTotalPrice(), totalSalesTax);
		orderItem.setTotalCost(itemCost);

		return orderItem;
	}

	/**
	 * Cart item.
	 *
	 * @param cartItem the cart item
	 * @return the order item builder
	 */
	public OrderItemBuilder cartItem(final CartItem cartItem) {
		this.cartItem = cartItem;
		return this;
	}

	/**
	 * Sales tax calculator.
	 *
	 * @param salesTaxCalculator the sales tax calculator
	 * @return the order item builder
	 */
	public OrderItemBuilder salesTaxCalculator(final SalesTaxCalculator salesTaxCalculator) {
		this.salesTaxCalculator = salesTaxCalculator;
		return this;
	}

}
