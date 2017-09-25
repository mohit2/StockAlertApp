package com.shanti.StockAlertApp.Services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shanti.StockAlertApp.Model.QuandleModel.CompositeKey;
import com.shanti.StockAlertApp.Model.QuandleModel.Response;
import com.shanti.StockAlertApp.Model.QuandleModel.StockPrice;
import com.shanti.StockAlertApp.Repositories.NSEStockRepository;
import com.shanti.StockAlertApp.Repositories.StockRepository;

@Service
public class QuandleHistoryServices {
	
	@Autowired
	private NSEStockRepository repository;
	@Autowired
	private StockRepository stockRepository;
	
	private static final String baseurl = "https://www.quandl.com/api/v3/datasets/NSE/";
	
	public void storeHistoricalData(String symbol) throws ParseException{
		String url = baseurl+ symbol + "/data.json";
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		Date dt = stockRepository.getMaxDateForStock(symbol);
		System.out.println(dt);
		builder = builder.queryParam("api_key", "xMH7BiBu6s24LHCizug3");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String strt_dt;
		if(dt!=null){
			c.setTime(dt);
			c.add(Calendar.DATE,1);
			strt_dt = sdf.format(c.getTime());
			System.out.println();
			builder.queryParam("start_date", strt_dt);
		}
		else{
			builder.queryParam("start_date", "2015-01-01");
		}
		
		ResponseEntity<Response> detail = restTemplate.getForEntity(builder.build().toUri(), Response.class);
		Response res =detail.getBody();
		List<List<Object>> data = res.getDataSet().getData();
		StockPrice price;
		CompositeKey key;
		List<StockPrice> stockPriceList = new ArrayList<>();
		for (List<Object> list : data) {
			price = new StockPrice();
			key = new CompositeKey();
			key.setSymbol(symbol);
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dte = format.parse((String)list.get(0));
			key.setDate(dte);
			price.setOpen((Double)list.get(1));
			price.setHigh((Double)list.get(2));
			price.setLow((Double)list.get(3));
			price.setLast((Double)list.get(4));
			price.setClose((Double)list.get(5));
			price.setTradeQuantity((Double)list.get(6));
			price.setTurnOver((Double)list.get(7));
			price.setKey(key);
			
			stockPriceList.add(price);
		}
		stockRepository.save(stockPriceList);
		
		
	}
	
	public List<String> getAllStocks(){
		
		return repository.getAllSymbols();
		
	}

	public void storeDataForAllStocks(List<String> stocks){
		
		stocks.stream().forEach(s-> System.out.println(s));
		stocks.stream().forEach(symbol-> {
			try {
				storeHistoricalData(symbol);
				System.out.println("Successful");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		/*try {
			storeHistoricalData("YESBANK");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
