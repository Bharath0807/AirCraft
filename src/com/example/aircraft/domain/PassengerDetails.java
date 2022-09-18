package com.example.aircraft.domain;

public class PassengerDetails {

	String firstName;
	String lastName;
	String pnr;
	String fareClass;
	String travelDate;
	String noOfPassengers;
	String ticketDate;
	String email;
	String phone;
	String bookedCabin;

	String errorMsg;
	boolean isValid = true;
	String applicableOffer;

	public String getApplicableOffer() {
		return applicableOffer;
	}

	public void setApplicableOffer(String applicableOffer) {
		this.applicableOffer = applicableOffer;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getFareClass() {
		return fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(String noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBookedCabin() {
		return bookedCabin;
	}

	public void setBookedCabin(String bookedCabin) {
		this.bookedCabin = bookedCabin;
	}

	public void getFare() {
		int castAscii = (int) this.fareClass.charAt(0);
		if (castAscii <= 69) {
			this.setApplicableOffer("OFFER_20");
		} else if (castAscii <= 75) {
			this.setApplicableOffer("OFFER_30");
		} else if (castAscii <= 82) {
			this.setApplicableOffer("OFFER_25");
		} else if (castAscii <= 75 || castAscii>=82) {
			this.setApplicableOffer("");
		}
	}
}
