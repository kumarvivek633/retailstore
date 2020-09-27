package com.vivek.retailstore.domain.scanning;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NonNull;

/**
 * The Class Scanner.
 */
@Entity
public class Scanner {

	/** The scanner id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long scannerId;

	/** The model name. */
	@NonNull
	private String modelName;

	/**
	 * Instantiates a new scanner.
	 */
	public Scanner() {
		super();
	}

	/**
	 * Instantiates a new scanner.
	 *
	 * @param modelName the model name
	 */
	public Scanner(@NonNull String modelName) {
		super();
		this.modelName = modelName;
	}

	/**
	 * Gets the scanner id.
	 *
	 * @return the scanner id
	 */
	public Long getScannerId() {
		return scannerId;
	}

	/**
	 * Sets the scanner id.
	 *
	 * @param scannerId the new scanner id
	 */
	public void setScannerId(Long scannerId) {
		this.scannerId = scannerId;
	}

	/**
	 * Gets the model name.
	 *
	 * @return the model name
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * Sets the model name.
	 *
	 * @param modelName the new model name
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
