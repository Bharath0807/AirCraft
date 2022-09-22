package com.example.aircraft.utils.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.utils.DataValidator;

public class ValidatorTest {

	@Test
	public void testEmail() {
		PassengerDetails detail = new PassengerDetails();
		detail.setEmail("@com");
		DataValidator.validate(detail);
		assertEquals("Email is not valid", detail.getErrorMsg());
	}
	
	@Test
	public void testMobileNum() {
		PassengerDetails detail = new PassengerDetails();
		detail.setEmail("bharath@gmail.com");
		detail.setPhone("8903434");
		DataValidator.validate(detail);
		assertEquals("Mobile number is not valid", detail.getErrorMsg());
	}
	
	@Test
	public void testTravelDate() {
		PassengerDetails detail = new PassengerDetails();
		detail.setEmail("bharath@gmail.com");
		detail.setPhone("8903434677");
		detail.setTravelDate("2-1-2022");
		detail.setTicketDate("10-1-2022");
		DataValidator.validate(detail);
		assertEquals("Ticketing date is before travel date ", detail.getErrorMsg());
	}
	
	@Test
	public void testValidatePnr() {
		PassengerDetails detail = new PassengerDetails();
		detail.setEmail("bharath@gmail.com");
		detail.setPhone("8903434677");
		detail.setTravelDate("2-1-2022");
		detail.setTicketDate("1-1-2022");
		detail.setPnr("1223");
		DataValidator.validate(detail);
		assertEquals("Pnr: contains More than 6 character", detail.getErrorMsg());
		detail.setPnr("!@#@12");
		DataValidator.validate(detail);
		assertEquals("Pnr: contains special characters", detail.getErrorMsg());
	}
}
