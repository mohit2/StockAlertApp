package com.shanti.StockAlertApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanti.StockAlertApp.Services.ExcelUploadService;
import com.shanti.StockAlertApp.Services.ExpiryServices;

@RestController
public class ExpiryApi {
	
	@Autowired
	private ExpiryServices service;
	
	@Autowired
	private ExcelUploadService excelService;
	
	@GetMapping(value = "getExpiredMembers")
	public List<String> getExpiredMembers(){
		return service.getExpiredMembers();
	}
	
	@PostMapping(value="setActiveFlag")
	public void setActiveFlag(){
		
	}
	
	@PostMapping(value ="uploadInitialData")
	public void uploalInitDb(){
		excelService.ReadExcel("C:\\Users\\shant\\Downloads\\premium sales.xlsx");
	}
	
	@PostMapping(value ="/uploadInstaMojoData/{filename}")
	public void uploadInstaMojoDataDb(@PathVariable("filename") String filename){
		excelService.ReadInstaMojoExcel("C:\\Users\\shant\\Downloads\\" + filename + ".xlsx");
	}
	

}
