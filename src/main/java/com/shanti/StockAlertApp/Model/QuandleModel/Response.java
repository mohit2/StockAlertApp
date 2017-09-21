package com.shanti.StockAlertApp.Model.QuandleModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("dataset_data")
	private StockDetail dataSet;

	public StockDetail getDataSet() {
		return dataSet;
	}

	public void setDataSet(StockDetail dataSet) {
		this.dataSet = dataSet;
	}
	
	

}
