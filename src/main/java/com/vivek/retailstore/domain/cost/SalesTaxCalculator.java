package com.vivek.retailstore.domain.cost;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.vivek.retailstore.domain.shoppingcart.CartItem;

import lombok.NonNull;

/**
 * This class have method {@link #calculateSalesTax(CartItem)} to calculate the
 * Sales Tax on the Product Item added in a Shopping Cart.
 */
@Component
public class SalesTaxCalculator {

	/**
	 * Calculate sales tax.
	 *
	 * @param cartItem the cart item
	 * @return the big decimal
	 */
	public BigDecimal calculateSalesTax(CartItem cartItem) {
		@NonNull
		SalesTaxCategory salesTaxProductCategory = cartItem.getProduct().getSalesTaxProductCategory();
		return cartItem.getTotalPrice().multiply(salesTaxProductCategory.getSalesTaxApplicable())
				.divide(new BigDecimal(100));
	}
}
