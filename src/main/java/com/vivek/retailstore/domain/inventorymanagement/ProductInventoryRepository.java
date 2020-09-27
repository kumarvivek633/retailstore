package com.vivek.retailstore.domain.inventorymanagement;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vivek.retailstore.domain.productcatalog.Product;

/**
 * The Interface ProductInventoryRepository.
 */
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, Long> {

	/**
	 * Find by product.
	 *
	 * @param product the product
	 * @return the optional
	 */
	public Optional<ProductInventory> findByProduct(Product product);

}
