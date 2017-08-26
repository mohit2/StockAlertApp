package com.shanti.StockAlertApp.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.shanti.StockAlertApp.Model.Stock;

@Component
public class Utility {
	
	public Map<Integer, List<Double>> processResponse(String result, Stock stock){
			
			BufferedReader bufReader = new BufferedReader(new StringReader(result));
			String line = null;
			int counter = 0;
			List<String> str;
			
			Map<Integer, List<Double>> timeToPriceMap =new HashMap<>();
	
			try {
				while((line = bufReader.readLine()) !=null){
					counter++;
					if(counter<=7){
						continue;
					}
					List<Double> prices = new ArrayList<>();
					str = Arrays.asList(line.split(","));
					str.stream().forEach(s->prices.add(Double.parseDouble(s)));
					timeToPriceMap.put(counter-7, prices);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			
			return timeToPriceMap;
		}
		
		public Stock  getHighLow(Map<Integer, List<Double>> map, Stock stock, Integer days){
			
			Double highest =0.0, lowest =0.0;
			
			Double high, low;
			if(!map.isEmpty() && days==365){
				if(map.get(1).get(0) > stock.getYearHigh()){
					stock.setYearHigh(map.get(1).get(0));
				}
				if(map.get(1).get(1) < stock.getYearLow()){
					stock.setYearHigh(map.get(1).get(1));
				}
			}
			else if(!map.isEmpty() && days!=2){
				lowest = map.get(1).get(0);
				for (Entry<Integer, List<Double>> m : map.entrySet()) {
					high = m.getValue().get(0);
					low = m.getValue().get(1);
					if( high> highest){
						highest = high;
					}
					if(low< lowest){
						lowest = low;
					}		
				}
			}
			
			
			switch (days) {
			
			case 30:
				stock.setMonthHigh(highest);
				stock.setMonthLow(lowest);
				break;
			case 7:
				stock.setWeekHigh(highest);
				stock.setWeekLow(lowest);
				break;
			case 2:
				if(!map.isEmpty()){
					stock.setYesterdayHigh(map.get(1).get(0));
					stock.setYesterdayLow(map.get(1).get(1));
				}
				
				break;
			default:
				break;
			}
			
			return stock;
		}

}
