package com.example.aircraft.service;

import java.util.List;

import com.example.aircraft.domain.PassengerDetails;

public interface IFileUtils {

	public List<PassengerDetails> getFile(String fileLoc) throws Exception;
	
	void writeToFile(List<PassengerDetails> details) throws Exception;
}
