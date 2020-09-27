package com.vivek.retailstore.domain.checkout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import lombok.NonNull;

/**
 * The Class PurchaseOrderAggregate.
 */
public class PurchaseOrderAggregate {

	/** The purchase order. */
	@NonNull
	private PurchaseOrder purchaseOrder;

	/**
	 * Gets the purchase order.
	 *
	 * @return the purchase order
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * Instantiates a new purchase order aggregate.
	 *
	 * @param purchaseOrder the purchase order
	 */
	public PurchaseOrderAggregate(@NonNull PurchaseOrder purchaseOrder) {
		super();
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * A factory for creating PurchaseOrderAggregateAggregate objects.
	 */
	@Configuration
	public static class PurchaseOrderAggregateAggregateFactory {

		/**
		 * Gets the purchase order aggregate.
		 *
		 * @param purchaseOrder the purchase order
		 * @return the purchase order aggregate
		 */
		@Bean
		@Scope("prototype")
		public PurchaseOrderAggregate getPurchaseOrderAggregate(final PurchaseOrder purchaseOrder) {
			return new PurchaseOrderAggregate(purchaseOrder);
		}
	}
}
