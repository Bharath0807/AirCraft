package com.example.aircraft.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.aircraft.domain.PassengerDetails;
import com.example.aircraft.service.IFileUtils;

public class FileUtils implements IFileUtils {

	List<String> headers = Arrays.asList("firstName", "lastName", "pnr", "fareClass", "travelDate", "noOfPassengers",
			"ticketDate", "email", "phone", "bookedCabin");

	@Override
	public List<PassengerDetails> getFile(String fileLoc) throws IOException, Exception {
		File file = new File(fileLoc);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> itr = sheet.iterator();

		List<PassengerDetails> details = new ArrayList<>();
		int i = 0;
		boolean isDataPresent = false;
		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			PassengerDetails passenger = new PassengerDetails();
			Class<?> clazz = passenger.getClass();
			int colNum = 0;
			isDataPresent = false;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (i != 0) {
						isDataPresent = true;
						Field field = clazz.getDeclaredField(headers.get(colNum));
						field.setAccessible(true);
						field.set(passenger, cell.getStringCellValue().toString());
						colNum++;
						break;
					}
				case Cell.CELL_TYPE_NUMERIC:
					if (i != 0) {
						Field field = clazz.getDeclaredField(headers.get(colNum));
						field.setAccessible(true);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						field.set(passenger, String.valueOf(cell.getStringCellValue()));
						colNum++;
						break;
					}
				}
			}

			if (i != 0 && isDataPresent) {
				details.add(passenger);
			}
			i++;
		}
		wb.close();
		return details;
	}

	@Override
	public void writeToFile(List<PassengerDetails> details) throws Exception {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Creating a blank Excel sheet
		XSSFSheet sheet = workbook.createSheet("output");

		int rowNum = 0;
		int cellnum = 0;
		Row row = sheet.createRow(rowNum++);
		for (String h : headers) {
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(h);
		}
		Cell cell = row.createCell(cellnum++);
		cell.setCellValue("DiscountCode");
		cell = row.createCell(cellnum++);
		cell.setCellValue("Error");
		for (PassengerDetails d : details) {
			int colNum = 0;
			row = sheet.createRow(rowNum++);
			cellnum = 0;
			try {
				while (colNum != headers.size()) {
					Field field = d.getClass().getDeclaredField(headers.get(colNum++));
					field.setAccessible(true);
					cell = row.createCell(cellnum++);
					cell.setCellValue(field.get(d).toString());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (d.isValid()) {
				cell = row.createCell(cellnum++);
				cell.setCellValue(d.getApplicableOffer());
			} else {
				cellnum++;
				cell = row.createCell(cellnum++);
				cell.setCellValue(d.getErrorMsg());
			}
			System.out.println("");
		}
		FileOutputStream out = new FileOutputStream(new File("output.xlsx"));
		workbook.write(out);
		workbook.close();
		out.close();
	}

}
