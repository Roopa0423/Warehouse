package com.abc.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.abc.beans.SellProductBean;
import com.abc.beans.SupplyProductBean;

public class createExcelTest {
	public static int generateReport() throws FileNotFoundException, IOException {
		try {

			String strDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			// declare file name to be create
			String filename = "E:\\Warehouse Report_" + strDate + ".xls";
			// creating an instance of HSSFWorkbook class
			HSSFWorkbook workbook = new HSSFWorkbook();

			// Sheet 1 - Supplied Products
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("Supplied Product");
			// creating the 0th row using the createRow() method
			HSSFRow rowhead = sheet.createRow((short)0);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("Product Id");
			rowhead.createCell(1).setCellValue("Product Name");
			rowhead.createCell(2).setCellValue("Product Quantity");
			rowhead.createCell(3).setCellValue("Remaining Quantity");
			rowhead.createCell(4).setCellValue("Supplier Id");
			rowhead.createCell(5).setCellValue("Supplier Name");
			rowhead.createCell(6).setCellValue("Supply Date");
			rowhead.createCell(7).setCellValue("Status Of Delivery");

			List<SupplyProductBean> supplyList = ProductDao.viewSuppliedProducts();
			int i = 1;
			for (SupplyProductBean bean : supplyList) {
				HSSFRow row = sheet.createRow((short)i);
				row.createCell(0).setCellValue(bean.getPid());
				row.createCell(1).setCellValue(bean.getPname());
				row.createCell(2).setCellValue(bean.getQuantity());
				row.createCell(3).setCellValue(bean.getRemainingQuantity());
				row.createCell(4).setCellValue(bean.getSid());
				row.createCell(5).setCellValue(bean.getSname());
				row.createCell(6).setCellValue(bean.getDate().toString());
				row.createCell(7).setCellValue(bean.getSstatus());
				i++;
			}
			
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);

			// Sheet 2 - Sold Products
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheetSold = workbook.createSheet("Sold Product");
			// creating the 0th row using the createRow() method
			HSSFRow rowheadSold = sheet.createRow((short)0);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("Product Id");
			rowhead.createCell(1).setCellValue("Seller Id");
			rowhead.createCell(2).setCellValue("Sold Quantity");
			rowhead.createCell(3).setCellValue("Date Sold");

			List<SellProductBean> sellList = ProductDao.viewSoldProducts();
			i = 1;
			for (SellProductBean bean : sellList) {
				HSSFRow row = sheet.createRow((short)i);
				row.createCell(0).setCellValue(bean.getPid());
				row.createCell(1).setCellValue(bean.getSeid());
				row.createCell(2).setCellValue(bean.getPquantity());
				row.createCell(3).setCellValue(bean.getDate().toString());
				i++;
			}

			
			workbook.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
