package com.parking.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.parking.model.Vehicle;

public class Parking {

	int MAX_SIZE = 0;

	ArrayList<Integer> availableSlotList;
	Map<String, Vehicle> slotToCar;
	Map<String, String> registrationNumberToSlot;
	Map<Integer, ArrayList<String>> ageToRegistrationNumbers;

	public void createParkingLot(Integer lotCount) {
		try {
			this.MAX_SIZE = lotCount;
		} catch (Exception e) {
			System.out.println("Invalid lot count");
			System.out.println();
		}
		this.availableSlotList = new ArrayList<Integer>() {

			private static final long serialVersionUID = 5021459030760983248L;
		};
		for (int i = 1; i <= this.MAX_SIZE; i++) {
			availableSlotList.add(i);
		}
		this.slotToCar = new HashMap<String, Vehicle>();
		this.registrationNumberToSlot = new HashMap<String, String>();
		this.ageToRegistrationNumbers = new HashMap<Integer, ArrayList<String>>();
		System.out.println("Created parking lot with " + lotCount + " slots");
		System.out.println();
	}

	public void park(String regNo, Integer age) {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.slotToCar.size() == this.MAX_SIZE) {
			System.out.println("Sorry, parking lot is full");
			System.out.println();
		} else {
			Collections.sort(availableSlotList);
			String slot = availableSlotList.get(0).toString();
			Vehicle vehicle = new Vehicle(regNo, age);
			this.slotToCar.put(slot, vehicle);
			this.registrationNumberToSlot.put(regNo, slot);
			if (this.ageToRegistrationNumbers.containsKey(age)) {
				ArrayList<String> regNoList = this.ageToRegistrationNumbers.get(age);
				this.ageToRegistrationNumbers.remove(age);
				regNoList.add(regNo);
				this.ageToRegistrationNumbers.put(age, regNoList);
			} else {
				ArrayList<String> regNoList = new ArrayList<String>();
				regNoList.add(regNo);
				this.ageToRegistrationNumbers.put(age, regNoList);
			}
			System.out.println("Allocated slot number: " + slot);
			System.out.println(
					"Car with vehicle registration number " + regNo + " has been parked at slot number " + slot);
			System.out.println();
			availableSlotList.remove(0);
		}
	}

	public void leave(String slotNo) {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.slotToCar.size() > 0) {
			Vehicle vehicleToVacate = this.slotToCar.get(slotNo);
			if (vehicleToVacate != null) {
				this.slotToCar.remove(slotNo);
				this.registrationNumberToSlot.remove(vehicleToVacate.getRegistrationNumber());
				ArrayList<String> regNoList = this.ageToRegistrationNumbers.get(vehicleToVacate.getDriverAge());
				if (regNoList.contains(vehicleToVacate.getRegistrationNumber())) {
					regNoList.remove(vehicleToVacate.getRegistrationNumber());
				}
				// Add the Lot No. back to available slot list.
				this.availableSlotList.add(Integer.parseInt(slotNo));
				System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number "
						+ vehicleToVacate.getRegistrationNumber() + " left the space, the driver of the car was of age "
						+ vehicleToVacate.getDriverAge());
				System.out.println();
			} else {
				System.out.println("Slot number " + slotNo + " is already empty");
				System.out.println();
			}
		} else {
			System.out.println("Parking lot is empty");
			System.out.println();
		}
	}

	public void status() {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.slotToCar.size() > 0) {
			// Print the current status.
			System.out.println("Slot No.\tRegistration No.\tDriver's age");
			Vehicle vehicle;
			for (int i = 1; i <= this.MAX_SIZE; i++) {
				String key = Integer.toString(i);
				if (this.slotToCar.containsKey(key)) {
					vehicle = this.slotToCar.get(key);
					System.out.println(i + "\t\t" + vehicle.getRegistrationNumber() + "\t\t" + vehicle.getDriverAge());
				}
			}
			System.out.println();
		} else {
			System.out.println("Parking lot is empty");
			System.out.println();
		}
	}

	public void getRegistrationNumbersFromAge(Integer age) {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.ageToRegistrationNumbers.containsKey(age)) {
			ArrayList<String> regNoList = this.ageToRegistrationNumbers.get(age);
			System.out.println();
			for (int i = 0; i < regNoList.size(); i++) {
				if (!(i == regNoList.size() - 1)) {
					System.out.print(regNoList.get(i) + ",");
				} else {
					System.out.print(regNoList.get(i));
				}
			}
		} else {
			System.out.println("Not found");
			System.out.println();
		}
	}

	public void getSlotNumbersFromDriverAge(Integer age) {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.ageToRegistrationNumbers.containsKey(age)) {
			ArrayList<String> regNoList = this.ageToRegistrationNumbers.get(age);
			ArrayList<Integer> slotList = new ArrayList<Integer>();
			System.out.println();
			for (int i = 0; i < regNoList.size(); i++) {
				slotList.add(Integer.valueOf(this.registrationNumberToSlot.get(regNoList.get(i))));
			}
			Collections.sort(slotList);
			System.out.println("Solt numbers ");
			for (int j = 0; j < slotList.size(); j++) {
				if (!(j == slotList.size() - 1)) {
					System.out.print(slotList.get(j) + ",");
				} else {
					System.out.print(slotList.get(j));
				}
			}
			System.out.println();
		} else {
			System.out.println("No parked car matches the query");
			System.out.println();
		}
	}

	public void getSlotNumberFromRegistrationNumber(String regNo) {
		if (this.MAX_SIZE == 0) {
			System.out.println("Sorry, parking lot is not created");
			System.out.println();
		} else if (this.registrationNumberToSlot.containsKey(regNo)) {
			String slot = this.registrationNumberToSlot.get(regNo);
			System.out.println(
					"Car with vehicle registration number " + regNo + " has been parked at slot number " + slot);
		} else {
			System.out.println("No parked car matches the query");
			System.out.println();
		}
	}
}
