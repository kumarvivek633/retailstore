package com.vivek.retailstore.domain.shoppingcart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.vivek.retailstore.domain.checkout.PurchaseOrder;
import com.vivek.retailstore.domain.checkout.PurchaseOrderAggregate;
import com.vivek.retailstore.domain.checkout.PurchaseOrderBuilder;
import com.vivek.retailstore.domain.checkout.PurchaseOrderRepository;
import com.vivek.retailstore.domain.inventorymanagement.ProductInventory;
import com.vivek.retailstore.domain.inventorymanagement.ProductInventoryRepository;
import com.vivek.retailstore.domain.productcatalog.Product;
import com.vivek.retailstore.domain.productcatalog.ProductRepository;

/**
 * The Class ShoppingCartAggregate.
 */
public class ShoppingCartAggregate {

	/** The purchase order aggregate aggregate factory. */
	@Autowired
	private PurchaseOrderAggregate.PurchaseOrderAggregateAggregateFactory purchaseOrderAggregateAggregateFactory;

	/** The purchase order repository. */
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/** The product inventory repository. */
	@Autowired
	private ProductInventoryRepository productInventoryRepository;

	/** The shopping cart repository. */
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	/** The shopping cart. */
	@NonNull
	private ShoppingCart shoppingCart;

	/**
	 * Instantiates a new shopping cart aggregate.
	 *
	 * @param shoppingCart the shopping cart
	 */
	public ShoppingCartAggregate(@NonNull ShoppingCart shoppingCart) {
		super();
		this.shoppingCart = shoppingCart;
	}

	
	public PurchaseOrderAggregate.PurchaseOrderAggregateAggregateFactory getPurchaseOrderAggregateAggregateFactory() {
		return purchaseOrderAggregateAggregateFactory;
	}



	public PurchaseOrderRepository getPurchaseOrderRepository() {
		return purchaseOrderRepository;
	}



	public ProductRepository getProductRepository() {
		return productRepository;
	}



	public ProductInventoryRepository getProductInventoryRepository() {
		return productInventoryRepository;
	}



	public ShoppingCartRepository getShoppingCartRepository() {
		return shoppingCartRepository;
	}



	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}



	/**
	 * Adds the to cart.
	 *
	 * @param productId the product id
	 * @param quantity the quantity
	 * @throws InsufficientStockException the insufficient stock exception
	 */
	@Transactional
	public void addToCart(Long productId, BigDecimal quantity) throws InsufficientStockException {
		Product product = getProduct(productId);
		boolean isSufficientStockAvailable = isSufficientStockAvailable(product, quantity);
		if (isSufficientStockAvailable) {
			this.shoppingCart.getCartItemList().add(new CartItem(product, quantity));
		} else {
			throw new InsufficientStockException("Not sufficient Stock available to match the order ");
		}

	}

	/**
	 * Gets the product.
	 *
	 * @param productId the product id
	 * @return the product
	 */
	private Product getProduct(Long productId) {
		Optional<Product> product = this.productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new IllegalArgumentException("Invalid Product id");
		}
		return product.get();
	}

	/**
	 * Checks if is sufficient stock available.
	 *
	 * @param product the product
	 * @param quantity the quantity
	 * @return true, if is sufficient stock available
	 */
	private boolean isSufficientStockAvailable(Product product, BigDecimal quantity) {
		Optional<ProductInventory> productInventory = productInventoryRepository.findByProduct(product);
		return productInventory.filter(value -> value.getAvailableStock().compareTo(quantity) == 1).isPresent();
	}

	/**
	 * Gets the items in cart.
	 *
	 * @return the items in cart
	 */
	public List<CartItem> getItemsInCart() {
		return shoppingCart.getCartItemList();
	}

	/**
	 * Checkout cart.
	 *
	 * @return the purchase order aggregate
	 */
	@Transactional
	public PurchaseOrderAggregate checkoutCart() {
		PurchaseOrder purchaseOrder = createPurchaseOrderFromShoppingCart();
		this.purchaseOrderRepository.save(purchaseOrder);

		// update shopping cart entity
		this.shoppingCart.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
		this.shoppingCart.setCartStatus(ShoppingCart.CartStatus.CHECKEDOUT);
		this.shoppingCartRepository.save(this.shoppingCart);
		return purchaseOrderAggregateAggregateFactory.getPurchaseOrderAggregate(purchaseOrder);
	}

	/**
	 * Creates the purchase order from shopping cart.
	 *
	 * @return the purchase order
	 */
	private PurchaseOrder createPurchaseOrderFromShoppingCart() {
		PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder();
		purchaseOrderBuilder.shoppingCart(shoppingCart);
		List<CartItem> itemsInCart = getItemsInCart();
		itemsInCart.forEach(cartItem -> purchaseOrderBuilder.cartItem(cartItem));
		return purchaseOrderBuilder.build();
	}

	/**
	 * Gets the purchase order.
	 *
	 * @return the purchase order
	 */
	public PurchaseOrderAggregate getPurchaseOrder() {
		return null;
	}

	/**
	 * A factory for creating ShoppingCartAggregateAggregate objects.
	 */
	@Configuration
	public static class ShoppingCartAggregateAggregateFactory {

		/**
		 * Gets the shopping cart aggregate.
		 *
		 * @param shoppingCart the shopping cart
		 * @return the shopping cart aggregate
		 */
		@Bean
		@Scope("prototype")
		public ShoppingCartAggregate getShoppingCartAggregate(ShoppingCart shoppingCart) {
			return new ShoppingCartAggregate(shoppingCart);
		}
	}

}
