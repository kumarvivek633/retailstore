package com.vivek.retailstore.domain.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vivek.retailstore.domain.cost.Cost;
import com.vivek.retailstore.domain.cost.SalesTaxCalculator;
import com.vivek.retailstore.domain.shoppingcart.CartItem;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCart;
import com.vivek.retailstore.storefront.util.BeanUtil;

/**
 * The Class PurchaseOrderBuilder.
 */
public class PurchaseOrderBuilder {

	/** The shopping cart. */
	private ShoppingCart shoppingCart;

	/** The cart item list. */
	private List<CartItem> cartItemList = new ArrayList<>();

	/**
	 * Builds the.
	 *
	 * @return the purchase order
	 */
	public PurchaseOrder build() {
		if (shoppingCart == null) {
			throw new IllegalArgumentException(
					"Shopping is required as Purchase Order can be created from existing " + "shopping cart");
		}

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCustomerName(this.shoppingCart.getCustomerName());

		SalesTaxCalculator salesTaxCalculator = BeanUtil.getBean(SalesTaxCalculator.class);
		List<OrderItem> orderItemList = getOrderItems(salesTaxCalculator);
		purchaseOrder.setOrderItems(orderItemList);

		BigDecimal totalSalesTax = calculateTotalSalesTax(orderItemList);
		BigDecimal subTotalPrice = calculateSubtotalPrice(orderItemList);
		Cost totalPrice = new Cost(subTotalPrice, totalSalesTax);
		purchaseOrder.setTotalPrice(totalPrice);

		return purchaseOrder;
	}

	/**
	 * Calculate total sales tax.
	 *
	 * @param orderItemList the order item list
	 * @return the big decimal
	 */
	private BigDecimal calculateTotalSalesTax(List<OrderItem> orderItemList) {
		BigDecimal totalSalesTax = new BigDecimal(0);
		orderItemList.forEach(item -> totalSalesTax.add(item.getTotalCost().getSalesTax()));
		return totalSalesTax;
	}

	/**
	 * Calculate subtotal price.
	 *
	 * @param orderItemList the order item list
	 * @return the big decimal
	 */
	private BigDecimal calculateSubtotalPrice(List<OrderItem> orderItemList) {
		BigDecimal totalPrice = new BigDecimal(0);
		orderItemList.forEach(item -> totalPrice.add(item.getTotalCost().getTotal()));
		return totalPrice;
	}

	/**
	 * Gets the order items.
	 *
	 * @param salesTaxCalculator the sales tax calculator
	 * @return the order items
	 */
	private List<OrderItem> getOrderItems(SalesTaxCalculator salesTaxCalculator) {
		List<OrderItem> orderItemList = new ArrayList<>();
		cartItemList.forEach(cartItem -> {
			OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
			OrderItem orderItem = orderItemBuilder.cartItem(cartItem).salesTaxCalculator(salesTaxCalculator).build();
			orderItemList.add(orderItem);
		});
		return orderItemList;
	}

	/**
	 * Shopping cart.
	 *
	 * @param shoppingCart the shopping cart
	 * @return the purchase order builder
	 */
	public PurchaseOrderBuilder shoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
		return this;
	}

	/**
	 * Cart item.
	 *
	 * @param cartItem the cart item
	 * @return the purchase order builder
	 */
	public PurchaseOrderBuilder cartItem(CartItem cartItem) {
		this.cartItemList.add(cartItem);
		return this;
	}

}
