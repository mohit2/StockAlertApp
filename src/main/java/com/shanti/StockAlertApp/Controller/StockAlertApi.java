package com.shanti.StockAlertApp.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanti.StockAlertApp.Model.QuandleModel.Stock;
import com.shanti.StockAlertApp.Services.StockAlertServices;

@RestController
public class StockAlertApi {
	
	/*@Autowired
	private StockAlertServices service;
	
	@PostMapping(value ="postHighLowForVariousRange")
	public List<Stock> postHighLowRange(){
		
		List<String> stockSymbols = service.getAllStockSymbol();
		
		List<Stock> stocks;
		stocks = service.putHighLowForNdays(2, stockSymbols);
		stocks = service.putHighLowForNdays(7, stockSymbols);
		stocks = service.putHighLowForNdays(30, stockSymbols);
		
		return stocks;
	}
	
	@GetMapping(value="getHighLowForStock/{stock}")
	public Stock getHighLowForStock(@PathVariable("stock") String symbol){
		
		return service.getStockHighLowRange(symbol);
	}
	
	@PostMapping(value = "postPercentChangeDetail")
	public void postPercentChange(){
		
		//List<String> stockSymbols = service.getAllStockSymbol();
		
		List<String> stockSymbols = new ArrayList<>();
		stockSymbols.add("ACC");
		service.calculatePercentChangeInPriceAndVolume(stockSymbols, 5);
		service.calculatePercentChangeInPriceAndVolume(stockSymbols, 10);
		service.calculatePercentChangeInPriceAndVolume(stockSymbols, 30);
	}*/

}
