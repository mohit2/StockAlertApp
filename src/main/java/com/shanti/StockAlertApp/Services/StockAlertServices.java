package com.shanti.StockAlertApp.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shanti.StockAlertApp.Model.Stock;
import com.shanti.StockAlertApp.Model.Trend;
import com.shanti.StockAlertApp.Repositories.StockRepository;
import com.shanti.StockAlertApp.Repositories.TrendRepository;
import com.shanti.StockAlertApp.Utilities.Utility;

@Service
public class StockAlertServices {

	@Autowired
	private StockRepository repository;
	
	@Autowired
	private TrendRepository trendRepository;
	
	@Autowired
	private Utility utility;
	
	String baseUrl = "https://www.google.com/finance/getprices";
	
	public List<String> getAllStockSymbol(){
		
		return repository.getAllStockSymbol();
		
	}
	
	public Stock getStockHighLowRange(String symbol){
		return repository.findOne(symbol);
	}
	
	public List<Stock> putHighLowForNdays(Integer days, List<String> symbols){
		String dayString = getDateString(days);
		
		RestTemplate restTemplate = new RestTemplate();
		List<Stock> stockList = new ArrayList<Stock>();
		for (String symbol : symbols) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
			builder = builder.queryParam("q", symbol)
							 .queryParam("p", dayString)
							 .queryParam("f", "h,l")
							 .queryParam("x", "NSE");
			
			ResponseEntity<String> entity = restTemplate.getForEntity(builder.build().toUri(), String.class);
			String result = entity.getBody();
			Stock stock = repository.findOne(symbol);
			if(stock==null){
				stock = new Stock();
				stock.setName(symbol);
			}
			Map<Integer, List<Double>>  timeToPriceMap = utility.processResponse(result);
			stock = utility.getHighLow(timeToPriceMap,stock, days);
			repository.save(stock);
			stockList.add(stock);
		}
		return stockList;
	}

	private String getDateString(Integer days) {
		String dayString = "1d";
		switch (days) {
		case 365:
			dayString = "1d";
			break;
		case 30:
			dayString = "30d";
			break;
		case 7:
			dayString = "7d";
			break;
		case 10:
			dayString = "10d";
			break;
		case 5:
			dayString = "5d";
			break;
		
		case 2:
			dayString = "2d";
			break;

		default:
			break;
		}
		return dayString;
	}
	
	public void calculatePercentChangeInPriceAndVolume(List<String> symbols, Integer days){
		
		RestTemplate restTemplate = new RestTemplate();
		String dayString = getDateString(days);
		List<Trend> trendList = new ArrayList<>();
		
		for (String symbol : symbols) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
			builder = builder.queryParam("q", symbol)
							.queryParam("p", dayString)
							.queryParam("f", "c,v")
							.queryParam("x", "NSE");

			ResponseEntity<String> entity = restTemplate.getForEntity(builder.build().toUri(), String.class);
			String result = entity.getBody();
			Trend trend = trendRepository.findOne(symbol);
			if(trend==null){
				trend = new Trend();
				trend.setSymbol(symbol);
			}
			Map<Integer, List<Double>>  timeToPriceMap = utility.processResponse(result);
			/*trend =  utility.getPercentChange(timeToPriceMap, trend, days);
			trendRepository.save(trend);
			trendList.add(trend);*/
		}
		
		
	}
	
}
