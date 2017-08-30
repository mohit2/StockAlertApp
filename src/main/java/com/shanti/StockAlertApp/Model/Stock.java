package com.shanti.StockAlertApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "stock_historical_price")
public class Stock {
	
	@Id
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="name")
	private String name;

	@Column(name="yest_high")
	private Double yesterdayHigh;
	
	@Column(name="yest_low")
	private Double yesterdayLow;
	
	@Column(name="week_high")
	private Double weekHigh;
	
	@Column(name="week_low")
	private Double weekLow;
	
	@Column(name="month_high")
	private Double monthHigh;
	
	@Column(name="month_low")
	private Double monthLow;
	
	@Column(name="year_high")
	private Double yearHigh;
	
	@Column(name="year_low")
	private Double yearLow;

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

	public Double getYesterdayHigh() {
		return yesterdayHigh;
	}

	public void setYesterdayHigh(Double yesterdayHigh) {
		this.yesterdayHigh = yesterdayHigh;
	}

	public Double getYesterdayLow() {
		return yesterdayLow;
	}

	public void setYesterdayLow(Double yesterdayLow) {
		this.yesterdayLow = yesterdayLow;
	}

	public Double getWeekHigh() {
		return weekHigh;
	}

	public void setWeekHigh(Double weekHigh) {
		this.weekHigh = weekHigh;
	}

	public Double getWeekLow() {
		return weekLow;
	}

	public void setWeekLow(Double weekLow) {
		this.weekLow = weekLow;
	}

	public Double getMonthHigh() {
		return monthHigh;
	}

	public void setMonthHigh(Double monthHigh) {
		this.monthHigh = monthHigh;
	}

	public Double getMonthLow() {
		return monthLow;
	}

	public void setMonthLow(Double monthLow) {
		this.monthLow = monthLow;
	}

	public Double getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(Double yearHigh) {
		this.yearHigh = yearHigh;
	}

	public Double getYearLow() {
		return yearLow;
	}

	public void setYearLow(Double yearLow) {
		this.yearLow = yearLow;
	}
	
}
