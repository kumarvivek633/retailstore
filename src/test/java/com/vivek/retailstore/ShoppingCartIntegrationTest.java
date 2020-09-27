package com.vivek.retailstore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vivek.retailstore.domain.StoreFrontAggregate;
import com.vivek.retailstore.domain.StoreFrontService;
import com.vivek.retailstore.domain.inventorymanagement.ProductInventoryRepository;
import com.vivek.retailstore.domain.productcatalog.ProductRepository;
import com.vivek.retailstore.domain.shoppingcart.CartItem;
import com.vivek.retailstore.domain.shoppingcart.InsufficientStockException;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCart;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCartAggregate;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounterAggregate;
import com.vivek.retailstore.testdata.TestProduct;
import com.vivek.retailstore.testdata.TestProductInventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * The Class ShoppingCartIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShoppingCartIntegrationTest {

	/** The store front service. */
	@Autowired
	private StoreFrontService storeFrontService;

	/** The product inventory repository. */
	@Autowired
	private ProductInventoryRepository productInventoryRepository;

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/** The shopping counter name. */
	private static String SHOPPING_COUNTER_NAME = "MyStore";

	/** The customer name. */
	private static String CUSTOMER_NAME = "Vivek";

	/** The scanner name. */
	private static String SCANNER_NAME = "test";

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
		storeFront.setupNewShoppingCounter(SHOPPING_COUNTER_NAME, SCANNER_NAME);

		productRepository.save(TestProduct.phone);
		productRepository.save(TestProduct.phoneCase);

		productInventoryRepository.save(TestProductInventory.iphoneInventory);
		productInventoryRepository.save(TestProductInventory.iphoneCaseInventory);
	}

	/**
	 * Test start shopping.
	 */
	@Test
	public void testStartShopping() {
		Optional<ShoppingCounterAggregate> shoppingCounter = storeFrontService.getStoreFront()
				.getShoppingCounter(SHOPPING_COUNTER_NAME);
		assertTrue(shoppingCounter.isPresent());
		ShoppingCartAggregate shoppingCartAggregate = shoppingCounter.get().startShoppingCart(CUSTOMER_NAME);

		ShoppingCart shoppingCart = shoppingCartAggregate.getShoppingCart();
		assertNotNull(shoppingCart);
		assertEquals(ShoppingCart.CartStatus.CREATED, shoppingCart.getCartStatus());
		assertNotNull(shoppingCart.getCartId());
		assertNull(shoppingCart.getPurchaseOrderId());
		assertEquals(CUSTOMER_NAME, shoppingCart.getCustomerName());

	}

	/**
	 * Test get valid shopping cart.
	 */
	@Test
	public void testGetValidShoppingCart() {
		Optional<ShoppingCounterAggregate> shoppingCounter = storeFrontService.getStoreFront()
				.getShoppingCounter(SHOPPING_COUNTER_NAME);
		assertTrue(shoppingCounter.isPresent());
		ShoppingCartAggregate shoppingCartAggregate = shoppingCounter.get().startShoppingCart(CUSTOMER_NAME);

		ShoppingCart shoppingCart = shoppingCartAggregate.getShoppingCart();
		Long cartId = shoppingCart.getCartId();

		Optional<ShoppingCartAggregate> shoppingCart1 = shoppingCounter.get().getShoppingCart(cartId);
		assertTrue(shoppingCart1.isPresent());

	}

	/**
	 * Test adding items to shopping cart.
	 */
	@Test
	public void testAddingItemsToShoppingCart() {
		ShoppingCartAggregate shoppingCartAggregate = storeFrontService.getStoreFront()
				.getShoppingCounter(SHOPPING_COUNTER_NAME).get().startShoppingCart(CUSTOMER_NAME);
		try {
			shoppingCartAggregate.addToCart(TestProduct.phone.getProductId(), new BigDecimal(1));
			shoppingCartAggregate.addToCart(TestProduct.phoneCase.getProductId(), new BigDecimal(2));
		} catch (InsufficientStockException e) {
			fail("Inventory check failed while adding a product in shopping cart");
		}
		List<CartItem> itemsInCart = shoppingCartAggregate.getItemsInCart();
		assertEquals(2, itemsInCart.size());

	}
}
