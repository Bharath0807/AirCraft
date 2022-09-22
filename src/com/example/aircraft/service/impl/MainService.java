package com.example.aircraft.service.impl;

import java.io.IOException;
import java.util.List;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.utils.DataValidator;

public class MainService {

	FileUtils fileUtils = new FileUtils();

	DataValidator dataValidator = new DataValidator();

	public void getDiscountedFare(String fileName) throws IOException, Exception {
		List<PassengerDetails> passengerDetails = fileUtils.getFile(fileName);
		passengerDetails.forEach(e -> {
			this.validate(e);
			if (e.isValid())
				this.getFare(e);
		});

		fileUtils.writeToFile(passengerDetails);

	}

	private void getFare(PassengerDetails detail) {
		int castAscii = (int) detail.getFareClass().charAt(0);
		if (castAscii <= 69) {
			detail.setApplicableOffer("OFFER_20");
		} else if (castAscii <= 75) {
			detail.setApplicableOffer("OFFER_30");
		} else if (castAscii <= 82) {
			detail.setApplicableOffer("OFFER_25");
		} else if (castAscii <= 75 || castAscii >= 82) {
			detail.setApplicableOffer("");
		}
	}

	/**
	 * Validate method to validate all the params from input
	 * 
	 * @param detail
	 */
	private void validate(PassengerDetails detail) {
		dataValidator.checkForValidEmail(detail);
		dataValidator.validateMobilephone(detail);
		dataValidator.validateTravelDateWithTicketDate(detail);
		dataValidator.checkForValidPnr(detail);
		dataValidator.checkForAvailableEconomy(detail);

	}
}