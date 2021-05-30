package com.parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.parking.operations.ReadInput;

public class ParkingApp {

	public static void main(String[] args) {
		ReadInput input = new ReadInput();
		System.out.println("Please enter 'exit' to quit");
		System.out.println("Waiting for input...");
		for (;;) {
			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String inputString = bufferRead.readLine();
				if (inputString.equalsIgnoreCase("exit")) {
					break;
				} else if ((inputString == null) || (inputString.isEmpty())) {
				} else {
					input.asText(inputString.trim());
				}
			} catch (IOException e) {
				System.out.println("An unexpected error occured while reading the input. Please try again");
				e.printStackTrace();
			}
		}

	}

}
