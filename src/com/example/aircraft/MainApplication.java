package com.example.aircraft;

import com.example.aircraft.service.impl.MainService;

public class MainApplication {

	public static void main(String[] args) throws Exception{
		MainService fileUtils = new MainService();
		fileUtils.getDiscountedFare("input.xlsx");
	}
}
