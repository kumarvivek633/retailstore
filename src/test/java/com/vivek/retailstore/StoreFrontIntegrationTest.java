package com.vivek.retailstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vivek.retailstore.domain.StoreFrontAggregate;
import com.vivek.retailstore.domain.StoreFrontService;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounterAggregate;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * The Class StoreFrontIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class StoreFrontIntegrationTest {

	/** The store front service. */
	@Autowired
	private StoreFrontService storeFrontService;

	/**
	 * Test setup new counter and get it.
	 */
	@Test
	public void testSetupNewCounterAndGetIt() {
		StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
		storeFront.setupNewShoppingCounter("First", "test");

		Optional<ShoppingCounterAggregate> firstCounter = storeFront.getShoppingCounter("First");
		assertTrue(firstCounter.isPresent());
	}

	/**
	 * Test setting up duplicate counter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSettingUpDuplicateCounter() {
		StoreFrontAggregate storeFront = storeFrontService.getStoreFront();
		storeFront.setupNewShoppingCounter("Counter1", "test1");
		storeFront.setupNewShoppingCounter("Counter1", "test1");

	}

	/**
	 * Test get non existing shopping counter.
	 */
	@Test
	public void testGetNonExistingShoppingCounter() {
		StoreFrontAggregate storeFront = storeFrontService.getStoreFront();

		Optional<ShoppingCounterAggregate> firstCounter = storeFront.getShoppingCounter("Invalid Counter");
		assertFalse(firstCounter.isPresent());
	}

}
