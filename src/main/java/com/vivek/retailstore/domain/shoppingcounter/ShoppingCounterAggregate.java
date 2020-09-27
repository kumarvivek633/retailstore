package com.vivek.retailstore.domain.shoppingcounter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.vivek.retailstore.domain.productcatalog.Product;
import com.vivek.retailstore.domain.productcatalog.ProductRepository;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCart;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCartAggregate;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCartRepository;

import lombok.NonNull;

/**
 * The Class ShoppingCounterAggregate.
 */
public class ShoppingCounterAggregate {

	/** The shopping cart aggregate aggregate factory. */
	@Autowired
	private ShoppingCartAggregate.ShoppingCartAggregateAggregateFactory shoppingCartAggregateAggregateFactory;

	/** The shopping cart repository. */
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/** The shopping counter. */
	@NonNull
	private ShoppingCounter shoppingCounter;

	/**
	 * Instantiates a new shopping counter aggregate.
	 *
	 * @param shoppingCounter the shopping counter
	 */
	public ShoppingCounterAggregate(@NonNull ShoppingCounter shoppingCounter) {
		super();
		this.shoppingCounter = shoppingCounter;
	}

	/**
	 * Start shopping cart.
	 *
	 * @param forCustomer the for customer
	 * @return the shopping cart aggregate
	 */
	@Transactional
	public ShoppingCartAggregate startShoppingCart(String forCustomer) {
		ShoppingCart cart = new ShoppingCart(forCustomer);
		this.shoppingCartRepository.save(cart);

		ShoppingCartAggregate shoppingCartAggregate = shoppingCartAggregateAggregateFactory
				.getShoppingCartAggregate(cart);
		return shoppingCartAggregate;
	}

	/**
	 * Gets the shopping cart.
	 *
	 * @param shoppingCartId the shopping cart id
	 * @return the shopping cart
	 */
	public Optional<ShoppingCartAggregate> getShoppingCart(Long shoppingCartId) {
		Optional<ShoppingCart> cart = this.shoppingCartRepository.findById(shoppingCartId);
		return cart.map(value -> shoppingCartAggregateAggregateFactory.getShoppingCartAggregate(value));
	}

	/**
	 * Scan product.
	 *
	 * @param barCode the bar code
	 * @return the optional
	 */
	public Optional<Product> scanProduct(String barCode) {
		return productRepository.findByBarCode(barCode);
	}

	/**
	 * A factory for creating ShoppingCounterAggregate objects.
	 */
	@Configuration
	public static class ShoppingCounterAggregateFactory {

		/**
		 * Gets the shopping counter aggregate.
		 *
		 * @param shoppingCounter the shopping counter
		 * @return the shopping counter aggregate
		 */
		@Bean
		@Scope("prototype")
		public ShoppingCounterAggregate getShoppingCounterAggregate(ShoppingCounter shoppingCounter) {
			return new ShoppingCounterAggregate(shoppingCounter);
		}
	}
}
