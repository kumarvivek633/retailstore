package com.vivek.retailstore.domain.productcatalog;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * The Interface ProductRepository.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

	/**
	 * Find by bar code.
	 *
	 * @param barCode the bar code
	 * @return the optional
	 */
	public Optional<Product> findByBarCode(String barCode);

}
