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
import com.vivek.retailstore.domain.checkout.PurchaseOrder;
import com.vivek.retailstore.domain.checkout.PurchaseOrderAggregate;
import com.vivek.retailstore.domain.inventorymanagement.ProductInventoryRepository;
import com.vivek.retailstore.domain.productcatalog.ProductRepository;
import com.vivek.retailstore.domain.shoppingcart.InsufficientStockException;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCart;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCartAggregate;
import com.vivek.retailstore.testdata.TestProduct;
import com.vivek.retailstore.testdata.TestProductInventory;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * The Class CheckoutIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CheckoutIntegrationTest {

	/** The store front service. */
	@Autowired
	private StoreFrontService storeFrontService;

	/** The shopping counter name. */
	private static String SHOPPING_COUNTER_NAME = "MyStore";

	/** The customer name. */
	private static String CUSTOMER_NAME = "Vivek";

	/** The scanner name. */
	private static String SCANNER_NAME = "test";

	/** The product inventory repository. */
	@Autowired
	private ProductInventoryRepository productInventoryRepository;

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

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

		PurchaseOrderAggregate purchaseOrderAggregate = shoppingCartAggregate.checkoutCart();
		assertNotNull(purchaseOrderAggregate);

		PurchaseOrder purchaseOrder = purchaseOrderAggregate.getPurchaseOrder();
		assertNotNull(purchaseOrder);

		assertEquals(2, purchaseOrder.getOrderItems().size());

		assertEquals(ShoppingCart.CartStatus.CHECKEDOUT, shoppingCartAggregate.getShoppingCart().getCartStatus());
	}

	/**
	 * Test inventory check on adding items to shopping cart.
	 *
	 * @throws InsufficientStockException the insufficient stock exception
	 */
	@Test(expected = InsufficientStockException.class)
	public void testInventoryCheckOnAddingItemsToShoppingCart() throws InsufficientStockException {
		ShoppingCartAggregate shoppingCartAggregate = storeFrontService.getStoreFront()
				.getShoppingCounter(SHOPPING_COUNTER_NAME).get().startShoppingCart(CUSTOMER_NAME);

		BigDecimal orderQuantity = TestProductInventory.iphoneInventory.getAvailableStock().add(new BigDecimal(1));
		shoppingCartAggregate.addToCart(TestProduct.phone.getProductId(), orderQuantity);

	}
}
