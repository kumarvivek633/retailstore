package com.vivek.retailstore.domain.shoppingcounter;

import javax.persistence.*;

import com.vivek.retailstore.domain.scanning.Scanner;

import lombok.NonNull;

/**
 * The Class ShoppingCounter.
 */
@Entity
public class ShoppingCounter {

	/** The counter id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long counterId;

	/** The counter name. */
	@NonNull
	private String counterName;

	/** The scanner. */
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	private Scanner scanner;

	/**
	 * Instantiates a new shopping counter.
	 */
	public ShoppingCounter() {
		super();
	}

	/**
	 * Instantiates a new shopping counter.
	 *
	 * @param counterId the counter id
	 * @param counterName the counter name
	 * @param scanner the scanner
	 */
	public ShoppingCounter(@NonNull String counterName, @NonNull Scanner scanner) {
		super();
		this.counterName = counterName;
		this.scanner = scanner;
	}

	/**
	 * Gets the counter id.
	 *
	 * @return the counter id
	 */
	public Long getCounterId() {
		return counterId;
	}

	/**
	 * Sets the counter id.
	 *
	 * @param counterId the new counter id
	 */
	public void setCounterId(Long counterId) {
		this.counterId = counterId;
	}

	/**
	 * Gets the counter name.
	 *
	 * @return the counter name
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * Sets the counter name.
	 *
	 * @param counterName the new counter name
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * Gets the scanner.
	 *
	 * @return the scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * Sets the scanner.
	 *
	 * @param scanner the new scanner
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

}
