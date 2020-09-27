package com.vivek.retailstore.domain.inventorymanagement;

import java.math.BigDecimal;
import javax.persistence.*;

import com.vivek.retailstore.domain.productcatalog.Product;

import lombok.NonNull;

/**
 * The Class ProductInventory.
 */
@Entity
public class ProductInventory {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The product. */
	@NonNull
	@OneToOne
	private Product product;

	/** The available stock. */
	@NonNull
	private BigDecimal availableStock;

	/**
	 * Instantiates a new product inventory.
	 */
	public ProductInventory() {
		super();
	}

	/**
	 * Instantiates a new product inventory.
	 *
	 * @param product the product
	 * @param availableStock the available stock
	 */
	public ProductInventory(@NonNull Product product, @NonNull BigDecimal availableStock) {
		super();
		this.product = product;
		this.availableStock = availableStock;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Gets the available stock.
	 *
	 * @return the available stock
	 */
	public BigDecimal getAvailableStock() {
		return availableStock;
	}

	/**
	 * Sets the available stock.
	 *
	 * @param availableStock the new available stock
	 */
	public void setAvailableStock(BigDecimal availableStock) {
		this.availableStock = availableStock;
	}

}
