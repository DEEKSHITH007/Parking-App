package com.parking.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadInput {

	private Commands commands;
	private Parking parking;

	public ReadInput() {
		super();
		this.commands = new Commands();
		this.parking = new Parking();
	}

	public void asText(String input) {
		String[] inputs = input.split(" ");
		switch (inputs.length) {
		case 1:
			try {
				Method method = commands.commandsMap.get(input);
				if (method != null && input.equalsIgnoreCase("status")) {
					method.invoke(parking);
				} else {
					asFile(input);
					System.out.println("Please provide a valid input");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Method method = commands.commandsMap.get(inputs[0]);
				if (method != null && inputs[1] != null) {
					if (inputs[0].equalsIgnoreCase("create_parking_lot")
							|| inputs[0].equalsIgnoreCase("slot_numbers_for_driver_of_age")) {
						method.invoke(parking, Integer.parseInt(inputs[1]));
					} else if (inputs[0].equalsIgnoreCase("slot_number_for_car_with_reg_number")
							|| inputs[0].equalsIgnoreCase("leave")) {
						method.invoke(parking, inputs[1]);
					}else {
						System.out.println("Please provide driver's age");
					}
				} else {
					System.out.println("Please provide a valid input");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				Method method = commands.commandsMap.get(inputs[0]);
				if (method != null && inputs[0].equalsIgnoreCase("park") && inputs[1] != null && inputs[2] != null) {
					method.invoke(parking, inputs[1], Integer.parseInt(inputs[2]));
				} else {
					System.out.println("Please provide a valid input");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Please provide a valid input input.");
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
