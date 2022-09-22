package com.example.aircraft.domain.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.utils.DataValidator;

public class PassengerDetailsTest {

	@Test
	public void testOffer() {
		PassengerDetails detail = new PassengerDetails();
		detail.setFareClass("A");
		detail.getFare();
		assertEquals("OFFER_20", detail.getApplicableOffer());
		
		detail.setFareClass("G");
		detail.getFare();
		assertEquals("OFFER_30", detail.getApplicableOffer());
		
		detail.setFareClass("Q");
		detail.getFare();
		assertEquals("OFFER_25", detail.getApplicableOffer());
		
		detail.setFareClass("Z");
		detail.getFare();
		assertEquals("", detail.getApplicableOffer());
	}
}
