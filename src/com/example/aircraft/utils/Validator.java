package com.example.aircraft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.aircraft.domain.PassengerDetails;

public class Validator {

	public static void validate(PassengerDetails detail) {
		validateEmail(detail);
	}

	static void validateEmail(PassengerDetails detail) {
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (detail.getEmail() == null || detail.getEmail().length() == 0) {
			detail.setErrorMsg("Email is not valid");
			detail.setValid(false);
			return;
		}
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(detail.getEmail());
		if (!matcher.find()) {
			detail.setErrorMsg("Email is not valid");
			detail.setValid(false);
			return;
		}
		validateMobilephone(detail);
	}

	static void validateMobilephone(PassengerDetails detail) {
		String data = detail.getPhone();
		try {
			if (data.length() == 10) {
				String regex = "\\d+";
				if (!data.matches(regex)) {
					detail.setErrorMsg("Mobile number is not valid");
					detail.setValid(false);
				}
			} else {
				detail.setErrorMsg("Mobile number is not valid");
				detail.setValid(false);
				return;
			}
		} catch (Exception e) {
			detail.setErrorMsg("Mobile number is not valid");
			detail.setValid(false);
			return;
		}
		validateDate(detail);
	}

	static void validateDate(PassengerDetails detail) {
		try {
			Date travelDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(detail.getTravelDate());
			Date ticketDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(detail.getTicketDate());
			long diff = travelDate1.getTime() - ticketDate1.getTime();
			if (diff < 0) {
				detail.setErrorMsg("Ticketing date is before travel date ");
				detail.setValid(false);
				return;
			}
		} catch (ParseException e) {
			detail.setErrorMsg("Ticketing date is before travel date ");
			detail.setValid(false);
			return;
		}
		validatePnr(detail);
	}

	static void validatePnr(PassengerDetails detail) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";

		Pattern p = Pattern.compile(regex);

		if (detail.getPnr() == null || detail.getPnr().length() != 6) {
			detail.setErrorMsg("Pnr: contains More than 6 character");
			detail.setValid(false);
			return;
		}

		Matcher m = p.matcher(detail.getPnr());

		if (!m.matches()) {
			detail.setErrorMsg("Pnr: contains special characters");
			detail.setValid(false);
			return;
		}
		validateEconomy(detail);
	}

	static List<String> list = Arrays.asList("Economy", "Premium Economy", "Business", "First");

	static void validateEconomy(PassengerDetails detail) {

		if (list.indexOf(detail.getBookedCabin()) == -1) {
			detail.setValid(false);
			detail.setErrorMsg("No Cabin named with this");
		}
	}
}
