package com.example.aircraft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.service.IValidator;

public class DataValidator implements IValidator {

	@Override
	public void checkForValidEmail(PassengerDetails detail) {
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
	}

	@Override
	public void validateMobilephone(PassengerDetails detail) {
		String data = detail.getPhone();
		String errorMsg = detail.isValid()?"Mobile number is not valid":detail.getErrorMsg()+",Mobile number is not valid";
		
		try {
			if (data.length() == 10) {
				String regex = "\\d+";
				if (!data.matches(regex)) {
					detail.setErrorMsg(errorMsg);
					detail.setValid(false);
				}
			} else {
				detail.setErrorMsg(errorMsg);
				detail.setValid(false);
				return;
			}
		} catch (Exception e) {
			detail.setErrorMsg(errorMsg);
			detail.setValid(false);
			return;
		}
	}

	@Override
	public void validateTravelDateWithTicketDate(PassengerDetails detail) {
		try {
			String errorMsg = detail.isValid()?"Ticketing date is before travel date ":detail.getErrorMsg()+",Ticketing date is before travel date ";
			Date travelDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(detail.getTravelDate());
			Date ticketDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(detail.getTicketDate());
			long diff = travelDate1.getTime() - ticketDate1.getTime();
			if (diff < 0) {
				detail.setErrorMsg(errorMsg);
				detail.setValid(false);
				return;
			}
		} catch (ParseException e) {
			detail.setErrorMsg(detail.isValid()?"":detail.getErrorMsg()+","+"Error in given date format, Expected is dd-MM-yyyy");
			detail.setValid(false);
			return;
		}
	}

	@Override
	public void checkForValidPnr(PassengerDetails detail) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";

		Pattern p = Pattern.compile(regex);

		if (detail.getPnr() == null || detail.getPnr().length() != 6) {
			detail.setErrorMsg(detail.isValid()?"":detail.getErrorMsg()+","+"Pnr: contains More than 6 character");
			detail.setValid(false);
			return;
		}

		Matcher m = p.matcher(detail.getPnr());

		if (!m.matches()) {
			detail.setErrorMsg(detail.isValid()?"":detail.getErrorMsg()+","+"Pnr: contains special characters");
			detail.setValid(false);
			return;
		}
	}

	static List<String> list = Arrays.asList("Economy", "Premium Economy", "Business", "First");

	@Override
	public void checkForAvailableEconomy(PassengerDetails detail) {

		if (list.indexOf(detail.getBookedCabin()) == -1) {
			detail.setValid(false);
			detail.setErrorMsg(detail.isValid()?"":detail.getErrorMsg()+","+"No Cabin named with this economy:"+detail.getBookedCabin());
		}
	}
}
