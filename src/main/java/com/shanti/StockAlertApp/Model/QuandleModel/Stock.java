package com.shanti.StockAlertApp.Model.QuandleModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "stock")
public class Stock {
	
	@Id
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="name")
	private String name;

	@Column(name="category")
	private String category;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
