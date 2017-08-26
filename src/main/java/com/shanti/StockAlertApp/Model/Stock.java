package com.shanti.StockAlertApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "stock")
public class Stock {
	
	@Id
	@Column(name="Symbol")
	private String symbol;
	
	@Column(name="Name")
	private String name;

	@Column(name="Yesterday_High")
	private Double yesterdayHigh;
	
	@Column(name="Yesterday_Low")
	private Double yesterdayLow;
	
	@Column(name="7_Day_High")
	private Double weekHigh;
	
	@Column(name="7_Day_Low")
	private Double weekLow;
	
	@Column(name="30_Day_High")
	private Double monthHigh;
	
	@Column(name="30_Day_Low")
	private Double monthLow;
	
	@Column(name="52_Week_High")
	private Double yearHigh;
	
	@Column(name="52_Week_Low")
	private Double yearLow;
	
	@Column(name="30_Day_Percentage_Change")
	private Double percentChange;

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

	public Double getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	
	
}
