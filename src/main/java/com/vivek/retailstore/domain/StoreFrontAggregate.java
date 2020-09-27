package com.vivek.retailstore.domain;

import java.util.Optional;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.vivek.retailstore.domain.scanning.Scanner;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounter;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounterAggregate;
import com.vivek.retailstore.domain.shoppingcounter.ShoppingCounterRepository;

/**
 * This class represents a single Online Store.
 */
@Getter
public class StoreFrontAggregate {

	/** The shopping counter aggregate factory. */
	@Autowired
	private ShoppingCounterAggregate.ShoppingCounterAggregateFactory shoppingCounterAggregateFactory;

	/** The shopping counter repository. */
	@Autowired
	private ShoppingCounterRepository shoppingCounterRepository;

	/**
	 * This method will a setup a new Shopping Counter in a store. Each shopping
	 * counter is identified by its unique shoppingCounterName within a Store.
	 *
	 * @param shoppingCounterName the shopping counter name
	 * @param scannerName the scanner name
	 */
	@Transactional
	public void setupNewShoppingCounter(String shoppingCounterName, String scannerName) {
		Optional<ShoppingCounter> shoppingCounter = shoppingCounterRepository.findByCounterName(shoppingCounterName);
		if (shoppingCounter.isPresent()) {
			throw new IllegalArgumentException("Shopping Counter with shoppingCounterName " + shoppingCounterName
					+ " already exists. Please setup a" + " counter with new Name");
		} else {
			Scanner scanner = new Scanner(scannerName);
			shoppingCounterRepository.save(new ShoppingCounter(shoppingCounterName, scanner));
		}
	}

	/**
	 * Gets the shopping counter.
	 *
	 * @param counterName the counter name
	 * @return the shopping counter
	 */
	public Optional<ShoppingCounterAggregate> getShoppingCounter(String counterName) {
		Optional<ShoppingCounter> shoppingCounter = shoppingCounterRepository.findByCounterName(counterName);
		return shoppingCounter.map(value -> {
			return shoppingCounterAggregateFactory.getShoppingCounterAggregate(value);
		});
	}

	/**
	 * A factory for creating StoreFrontAggregate objects.
	 */
	@Configuration
	public static class StoreFrontAggregateFactory {
		
		/**
		 * Gets the store front aggregate.
		 *
		 * @return the store front aggregate
		 */
		@Bean
		@Scope("prototype")
		public StoreFrontAggregate getStoreFrontAggregate() {
			return new StoreFrontAggregate();
		}
	}

}
