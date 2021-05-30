package com.parking.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadInputTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	ReadInput readInput = new ReadInput();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void caseCreateTest() {
		readInput.asText("create_parking_lot 5");
		assertEquals("Createdparkinglotwith5slots", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseCreateInvalidTest() {
		readInput.asText("create_parking_lot ");
		assertEquals("Enteravalidnumber", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseParkTest() {
		readInput.asText("park KA-18-Z-4681 24");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseParkInValidTest() {
		readInput.asText("create_parking_lot 5");
		readInput.asText("park 24");
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "PleaseprovideavalidRegistrationnumberanddriver'sage",
				outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseLeaveTest() {
		readInput.asText("leave 24");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseLeaveInvalidTest() {
		readInput.asText("leave");
		assertEquals("Pleaseentertheslottobevacated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseStatusTest() {
		readInput.asText("status");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseStatusInvalidTest() {
		readInput.asText("status 2");
		assertEquals("Pleaseprovideavalidinput", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseSlotNumbersFromDriversAgeTest() {
		readInput.asText("slot_numbers_for_driver_of_age 24");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseSlotNumbersFromDriversAgeInvalidTest() {
		readInput.asText("slot_numbers_for_driver_of_age");
		assertEquals("Pleaseprovideavaliddriver'sage", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseSlotNumbersFromRegNumberTest() {
		readInput.asText("slot_number_for_car_with_reg_number KA-18-Z-4681");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseSlotNumbersFromRegNumberInvalidTest() {
		readInput.asText("slot_number_for_car_with_reg_number");
		assertEquals("PleaseprovideavalidRegistrationnumber.", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseRegisterNumberFromAgeTest() {
		readInput.asText("register_number_from_age 24");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void caseRegisterNumberFromAgeInvalidTest() {
		readInput.asText("register_number_from_age");
		assertEquals("PleaseprovideavalidRegistrationnumber.", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void casefileInvalidTest() {
		readInput.asText("file");
		assertEquals("Pleaseprovideavalidfilename", outContent.toString().trim().replace(" ", ""));
	}
	
	@Test
	public void defaultTest() {
		readInput.asText("invalidCommand");
		assertEquals("Pleaseenteravalidcommand", outContent.toString().trim().replace(" ", ""));
	}
	
}
