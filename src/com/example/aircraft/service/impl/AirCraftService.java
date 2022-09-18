package com.example.aircraft.service.impl;

import java.io.IOException;
import java.util.List;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.utils.Validator;

public class AirCraftService {

	FileUtils fileUtils = new FileUtils();

	public void allocate(String fileName) throws IOException, Exception {
		List<PassengerDetails> passengerDetails = fileUtils.getFile(fileName);
		passengerDetails.forEach(e -> Validator.validate(e));

		passengerDetails.stream().filter(e -> e.isValid()).forEach(e -> {
			e.getFare();
		});

		fileUtils.writeToFile(passengerDetails);

	}
}
