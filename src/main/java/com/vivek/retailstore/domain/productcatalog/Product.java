package com.vivek.retailstore.domain.productcatalog;

import java.math.BigDecimal;
import javax.persistence.*;

import com.vivek.retailstore.domain.cost.SalesTaxCategory;

import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The Class Product.
 */
@Entity
@NoArgsConstructor
public class Product {

	/** The product id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	/** The product name. */
	@NonNull
	private String productName;

	/** The bar code. */
	@NonNull
	private String barCode;

	/** The sales tax product category. */
	@NonNull
	@Enumerated
	private SalesTaxCategory salesTaxProductCategory;

	/** The unit price. */
	@NonNull
	private BigDecimal unitPrice;

	/**
	 * Instantiates a new product.
	 *
	 * @param productName             the product name
	 * @param barCode                 the bar code
	 * @param salesTaxProductCategory the sales tax product category
	 * @param unitPrice               the unit price
	 */
	public Product(@NonNull String productName, @NonNull String barCode,
			@NonNull SalesTaxCategory salesTaxProductCategory, @NonNull BigDecimal unitPrice) {
		super();
		this.productName = productName;
		this.barCode = barCode;
		this.salesTaxProductCategory = salesTaxProductCategory;
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the bar code.
	 *
	 * @return the bar code
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * Sets the bar code.
	 *
	 * @param barCode the new bar code
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * Gets the sales tax product category.
	 *
	 * @return the sales tax product category
	 */
	public SalesTaxCategory getSalesTaxProductCategory() {
		return salesTaxProductCategory;
	}

	/**
	 * Sets the sales tax product category.
	 *
	 * @param salesTaxProductCategory the new sales tax product category
	 */
	public void setSalesTaxProductCategory(SalesTaxCategory salesTaxProductCategory) {
		this.salesTaxProductCategory = salesTaxProductCategory;
	}

	/**
	 * Gets the unit price.
	 *
	 * @return the unit price
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Sets the unit price.
	 *
	 * @param unitPrice the new unit price
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
