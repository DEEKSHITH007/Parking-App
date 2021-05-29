package com.parking.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Vehicle.
 */
public class Vehicle {

	/** The registration number. */
	private String registrationNumber;
	
	/** The driver age. */
	private Integer driverAge;
	
	/**
	 * Instantiates a new vehicle.
	 *
	 * @param registrationNumber the registration number
	 * @param driverAge the driver age
	 */
	public Vehicle(String registrationNumber, Integer driverAge) {
		super();
		this.registrationNumber = registrationNumber;
		this.driverAge = driverAge;
	}


	/**
	 * Gets the registration number.
	 *
	 * @return the registration number
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * Sets the registration number.
	 *
	 * @param registrationNumber the new registration number
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * Gets the driver age.
	 *
	 * @return the driver age
	 */
	public Integer getDriverAge() {
		return driverAge;
	}

	/**
	 * Sets the driver age.
	 *
	 * @param driverAge the new driver age
	 */
	public void setDriverAge(Integer driverAge) {
		this.driverAge = driverAge;
	}

	
}
