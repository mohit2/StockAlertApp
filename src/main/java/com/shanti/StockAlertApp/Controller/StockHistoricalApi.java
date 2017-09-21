package com.shanti.StockAlertApp.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanti.StockAlertApp.Model.QuandleModel.StockDetail;
import com.shanti.StockAlertApp.Services.QuandleHistoryServices;

@RestController
public class StockHistoricalApi {
	
	@Autowired
	private QuandleHistoryServices services;
	
	@GetMapping("/getData")
	public void getData() throws ParseException{
		// services.getHistoricalData("YESBANK");
	}
	
	@PostMapping("/StoreHistoricalData")
	public String storeDataForStocks(){
		
		List<String> symbols = services.getAllStocks();
		services.storeDataForAllStocks(symbols);
		return "successful";
	}

}
