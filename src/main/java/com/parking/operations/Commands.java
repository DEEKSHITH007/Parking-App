package com.parking.operations;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Commands {

	 public Map<String, Method> commandsMap;

	    public Commands() {
	        commandsMap = new HashMap<String, Method>();
	        try {
	            populateCommandsHashMap();
	        } catch (NoSuchMethodException e) {
	            e.printStackTrace();
	        }
	    }
	    private void populateCommandsHashMap() throws NoSuchMethodException {
	        commandsMap.put("create_parking_lot", Parking.class.getMethod("createParkingLot", Integer.class));
	        commandsMap.put("park", Parking.class.getMethod("park", String.class, Integer.class));
	        commandsMap.put("leave", Parking.class.getMethod("leave", String.class));
	        commandsMap.put("status", Parking.class.getMethod("status"));
	        commandsMap.put("slot_numbers_for_driver_of_age", Parking.class.getMethod("getSlotNumbersFromDriverAge", Integer.class));
	        commandsMap.put("slot_number_for_car_with_reg_number", Parking.class.getMethod("getSlotNumberFromRegistrationNumber", String.class));
	    }
}
