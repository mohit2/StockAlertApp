package com.shanti.StockAlertApp.Services;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.shanti.StockAlertApp.Model.PremiumMember;
import com.shanti.StockAlertApp.Repositories.MemberRepository;

@Service
public class ExcelUploadService {
	
	@Autowired
	private MemberRepository repository;
	
	public void setActiveFlag(){
		
	}
	public void ReadExcel(String filePath){
		
		File file = new File(filePath);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
	
				if(row.getRowNum() ==0){
					continue;
				}
				String email = row.getCell(0).getStringCellValue();
				PremiumMember member = repository.findOne(email);
				
				if(member!=null){
					member.setSubcriptionDate(row.getCell(4).getDateCellValue());
					Calendar c = Calendar.getInstance();
					c.setTime(member.getSubcriptionDate());
					c.add(Calendar.DATE, 30);
					member.setExpirationDate(c.getTime());
				}
				else{
					member = new PremiumMember();
					member.setEmail(row.getCell(0).getStringCellValue());
					member.setName(row.getCell(1).getStringCellValue());
					if(member.getEmail().contains("gmail")){
						member.setGmail(row.getCell(0).getStringCellValue());
					}	
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					if(row.getCell(2) == null){
						member.setPhnNo("");
					}
					else{
						member.setPhnNo(row.getCell(2).getStringCellValue());
					}			
					member.setSubcriptionDate(row.getCell(3).getDateCellValue());
					Calendar c = Calendar.getInstance();
					c.setTime(member.getSubcriptionDate());
					c.add(Calendar.DATE, 30);
					member.setExpirationDate(c.getTime());		
				}
				if(new Date().compareTo(member.getExpirationDate()) > 0){
					member.setActive("N");
				}
				else{
					member.setActive("Y");
				}
				
				repository.save(member);
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ReadInstaMojoExcel(String filePath){
	
		File file = new File(filePath);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
	
				if(row.getRowNum() ==0){
					continue;
				}
				String email = row.getCell(4).getStringCellValue();
				PremiumMember member = repository.findOne(email);
				row.getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
				if(member!=null){
					Calendar c = Calendar.getInstance();
					if(member.getActive().equals("Y")){
						//System.out.println("1st 2nd:" + row.getCell(1).getDateCellValue()+ " 2nd" + member.getSubcriptionDate());
						
						if(row.getCell(1).getDateCellValue().compareTo(member.getSubcriptionDate()) > 0){
							c.setTime(member.getSubcriptionDate());
							c.add(Calendar.DATE, 30);
							member.setSubcriptionDate(c.getTime());
							c.setTime(member.getExpirationDate());
							c.add(Calendar.DATE, 30);
							member.setExpirationDate(c.getTime());
						}
							
					}
					else{
						member.setSubcriptionDate(row.getCell(1).getDateCellValue());
						c.setTime(member.getSubcriptionDate());
						c.add(Calendar.DATE, 30);
						member.setExpirationDate(c.getTime());
					}
					
				}
				else{
					member = new PremiumMember();
					member.setEmail(row.getCell(4).getStringCellValue());
					member.setName(row.getCell(3).getStringCellValue());
					if(row.getCell(4).getStringCellValue().contains("gmail")){
						member.setGmail(row.getCell(4).getStringCellValue());
					}
					else{
						member.setGmail("");
					}		
					row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					if(row.getCell(5) == null){
						member.setPhnNo("");
					}
					else{
						member.setPhnNo(row.getCell(5).getStringCellValue());
					}
					member.setSubcriptionDate(row.getCell(1).getDateCellValue());
					Calendar c = Calendar.getInstance();
					c.setTime(member.getSubcriptionDate());
					c.add(Calendar.DATE, 30);
					member.setExpirationDate(c.getTime());		
				}
				if(new Date().compareTo(member.getExpirationDate()) > 0){
					member.setActive("N");
				}
				else{
					member.setActive("Y");
				}
				
				repository.save(member);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
