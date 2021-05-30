package com.parking.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingTest {

	Parking parking = new Parking();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void zeroAsCreateParkingTest() {
		parking.createParkingLot(-1);
		assertEquals("Pleaseprovideavaluegreaterthanzero", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void successfulCreation() {
		parking.createParkingLot(5);
		assertEquals(5, parking.MAX_SIZE);
		assertEquals(5, parking.availableSlotList.size());
		assertEquals("Createdparkinglotwith5slots", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void successfulParkTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		assertEquals(4, parking.availableSlotList.size());
		assertEquals(
				"Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
						+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1",
				outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void parkingFullTest() {
		parking.createParkingLot(1);
		parking.park("KA-18-Z-4681", 24);
		parking.park("KA-18-Z-6481", 24);

		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		list.add("KA-18-Z-4681");
		map.put(Integer.valueOf(24), list);

		assertEquals(map, parking.ageToRegistrationNumbers);
		assertEquals("Createdparkinglotwith1slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Sorry,parkinglotisfull", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void parkMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.park("KA-18-Z-6481", 24);
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void leaveSuccessTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		assertEquals(4, parking.availableSlotList.size());
		parking.leave("1");
		assertEquals(5, parking.availableSlotList.size());
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Slotnumber1vacated,thecarwithvehicleregistrationnumberKA-18-Z-4681leftthespace,thedriverofthecarwasofage24",
				outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void leaveParkingLotEmptyTest() {
		parking.createParkingLot(5);
		parking.leave("1");
		assertEquals(5, parking.availableSlotList.size());
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Parkinglotisempty",
				outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void leaveSlotEmptysTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		assertEquals(4, parking.availableSlotList.size());
		parking.leave("1");
		parking.leave("1");
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Slotnumber1vacated,thecarwithvehicleregistrationnumberKA-18-Z-4681leftthespace,thedriverofthecarwasofage24\n"
				+ "\n" + "Parkinglotisempty", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void leaveMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.leave("1");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void statusSuccessTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		parking.status();
		assertEquals(4, parking.availableSlotList.size());
		assertEquals(
				"Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
						+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
						+ "SlotNo.	RegistrationNo.	Driver'sage\n" + "1		KA-18-Z-4681		24",
				outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void statusParkingEmptyTest() {
		parking.createParkingLot(5);
		parking.status();
		assertEquals(5, parking.availableSlotList.size());
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Parkinglotisempty",
				outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void statusMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.status();
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void getRegistrationFromAgeTest() {
		parking.createParkingLot(3);
		parking.park("KA-18-Z-4681", 24);
		parking.park("KA-18-Z-6481", 24);

		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		list.add("KA-18-Z-4681");
		list.add("KA-18-Z-6481");
		map.put(Integer.valueOf(24), list);

		parking.getRegistrationNumbersFromAge(24);

		assertEquals(map, parking.ageToRegistrationNumbers);
		assertEquals("Createdparkinglotwith3slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Allocatedslotnumber:2\n" + "CarwithvehicleregistrationnumberKA-18-Z-6481hasbeenparkedatslotnumber2\n"
				+ "\n" + "\n" + "KA-18-Z-4681,KA-18-Z-6481", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getRegistrationFromAgeNotFoundTest() {
		parking.createParkingLot(3);
		parking.park("KA-18-Z-4681", 24);
		parking.park("KA-18-Z-6481", 24);

		parking.getRegistrationNumbersFromAge(44);

		assertEquals("Createdparkinglotwith3slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Allocatedslotnumber:2\n" + "CarwithvehicleregistrationnumberKA-18-Z-6481hasbeenparkedatslotnumber2\n"
				+ "\n" + "Notfound", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getRegistrationFromAgeMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.getRegistrationNumbersFromAge(24);
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void getSlotNumbersFromDriverAgeTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		parking.getSlotNumbersFromDriverAge(24);
		assertEquals(2, parking.availableSlotList.get(0));
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n" + "\n"
				+ "Soltnumbers\n" + "1", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getSlotNumbersFromDriverAgeNotFoundTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		parking.getSlotNumbersFromDriverAge(34);
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Noparkedcarmatchesthequery", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getSlotNumbersFromDriverAgeTestMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.getSlotNumbersFromDriverAge(24);
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}

	@Test
	public void getSlotNumberFromRegistrationNumberTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		parking.getSlotNumberFromRegistrationNumber("KA-18-Z-4681");
		assertEquals(2, parking.availableSlotList.get(0));
		assertEquals(
				"Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
						+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
						+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1",
				outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getSlotNumberFromRegistrationNumberNotFoundTest() {
		parking.createParkingLot(5);
		parking.park("KA-18-Z-4681", 24);
		parking.getSlotNumberFromRegistrationNumber("KA-18-Z-4682");
		assertEquals("Createdparkinglotwith5slots\n" + "\n" + "Allocatedslotnumber:1\n"
				+ "CarwithvehicleregistrationnumberKA-18-Z-4681hasbeenparkedatslotnumber1\n" + "\n"
				+ "Noparkedcarmatchesthequery", outContent.toString().trim().replace(" ", ""));
	}

	@Test
	public void getSlotNumberFromRegistrationNumberTestMaxSizeZero() {
		parking.MAX_SIZE = 0;
		parking.getSlotNumberFromRegistrationNumber("KA-18-Z-4681");
		assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));

	}
}
