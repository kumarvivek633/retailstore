package com.vivek.retailstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vivek.retailstore.domain.checkout.PurchaseOrder;
import com.vivek.retailstore.domain.checkout.PurchaseOrderBuilder;
import com.vivek.retailstore.domain.shoppingcart.CartItem;
import com.vivek.retailstore.domain.shoppingcart.ShoppingCart;
import com.vivek.retailstore.testdata.TestProduct;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class PurchaseOrderBuilderTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderBuilderTest {

	/**
	 * Test purchase order builder.
	 */
	@Test
	public void testPurchaseOrderBuilder() {
		String customerName = "Vivek";
		ShoppingCart shoppingCart = new ShoppingCart(customerName);

		CartItem iphoneItem = new CartItem(TestProduct.phone, new BigDecimal(1));

		CartItem iphoneCaseItem = new CartItem(TestProduct.phoneCase, new BigDecimal(1));

		PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder();
		PurchaseOrder purchaseOrder = purchaseOrderBuilder.shoppingCart(shoppingCart).cartItem(iphoneItem)
				.cartItem(iphoneCaseItem).build();

		assertNotNull(purchaseOrder);
		assertEquals(2, purchaseOrder.getOrderItems().size());
		assertEquals(customerName, purchaseOrder.getCustomerName());
	}
}
