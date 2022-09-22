package com.example.aircraft.service;

import com.example.aircraft.domain.PassengerDetails;

public interface IValidator {

	 void checkForValidEmail(PassengerDetails detail);

	void checkForAvailableEconomy(PassengerDetails detail);

	void checkForValidPnr(PassengerDetails detail);

	void validateTravelDateWithTicketDate(PassengerDetails detail);

	void validateMobilephone(PassengerDetails detail);
}
