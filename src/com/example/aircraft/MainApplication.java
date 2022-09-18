package com.example.aircraft;

import com.example.aircraft.service.impl.AirCraftService;

public class MainApplication {

	public static void main(String[] args) throws Exception{
		AirCraftService fileUtils = new AirCraftService();
		fileUtils.allocate("input.xlsx");
	}
}
