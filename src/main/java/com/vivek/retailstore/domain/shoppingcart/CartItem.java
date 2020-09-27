package com.vivek.retailstore.domain.shoppingcart;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import com.vivek.retailstore.domain.productcatalog.Product;
import com.vivek.retailstore.storefront.util.BeanUtil;

/**
 * The Class CartItem.
 */
@Entity
public class CartItem {

	/** The cart item id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartItemId;

	/** The product. */
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;

	/** The quantity. */
	@NonNull
	private BigDecimal quantity;

	/**
	 * Instantiates a new cart item.
	 */
	public CartItem() {
		super();
	}

	/**
	 * Instantiates a new cart item.
	 *
	 * @param product the product
	 * @param quantity the quantity
	 */
	public CartItem(@NonNull Product product, @NonNull BigDecimal quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * Gets the cart item id.
	 *
	 * @return the cart item id
	 */
	public Long getCartItemId() {
		return cartItemId;
	}

	/**
	 * Sets the cart item id.
	 *
	 * @param cartItemId the new cart item id
	 */
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public BigDecimal getTotalPrice() {
		TotalCalculator totalCalculator = BeanUtil.getBean(TotalCalculator.class);
		return totalCalculator.calculate(this.product.getUnitPrice(), this.quantity);
	}

	/**
	 * The Class TotalCalculator.
	 */
	@Component
	public static class TotalCalculator {

		/**
		 * Calculate.
		 *
		 * @param unitPrice the unit price
		 * @param quantity  the quantity
		 * @return the big decimal
		 */
		public BigDecimal calculate(BigDecimal unitPrice, BigDecimal quantity) {
			return quantity.multiply(unitPrice);
		}

	}
}
