package com.parking.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {

	private Parking parking;

	public ReadInput() {
		super();
		this.parking = new Parking();
	}

	public void asText(String input) {
		String[] inputs = input.split(" ");

		switch (inputs[0]) {
		case "create_parking_lot": {
			if (inputs.length == 2)
				parking.createParkingLot(Integer.parseInt(inputs[1]));
			else {
				System.out.println("Enter a valid number");

			}

		}
			break;
		case "park": {
			if (inputs.length == 3)
				parking.park(inputs[1], Integer.parseInt(inputs[2]));
			else {
				System.out.println("Please provide a valid Registration number and driver's age");
			}
		}
			break;
		case "leave": {
			if (inputs.length == 2)
				parking.leave(inputs[1]);
			else {
				System.out.println("Please enter the slot to be vacated");
			}
		}
			break;
		case "status": {
			if (inputs.length == 1)
				parking.status();
			else {
				System.out.println("Please provide a valid input");
			}

		}
			break;
		case "slot_numbers_for_driver_of_age": {
			if (inputs.length == 2)
				parking.getSlotNumbersFromDriverAge(Integer.parseInt(inputs[1]));
			else {
				System.out.println("Please provide a valid driver's age");
			}

		}
			break;
		case "slot_number_for_car_with_reg_number": {
			if (inputs.length == 2)
				parking.getSlotNumberFromRegistrationNumber(inputs[1]);
			else {
				System.out.println("Please provide a valid Registration number.");
			}
		}
			break;
		case "file": {
			if (inputs.length == 2)
				asFile(inputs[1]);
			else {
				System.out.println("Please provide a valid file name");
			}
		}
			break;
		default: {
			System.out.println("Please enter a valid command");

		}
			break;
		}

	}

	public void asFile(String file) {
		File inputFile = new File(file);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					asText(line.trim());
				}
			} catch (IOException ex) {
				System.out.println("An unexpected error occured while reading the file");
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Please specify a valid file path");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
