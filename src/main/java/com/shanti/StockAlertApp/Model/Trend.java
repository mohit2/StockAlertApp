package com.shanti.StockAlertApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_trend")
public class Trend {

	@Id
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="name")
	private String name;
	
	@Column(name="threeDayChangePct")
	private Double fiveDayPriceChange;
	
	@Column(name="weekChangePct")
	private Double weekChangePct;
	
	@Column(name="monthChangePct")
	private Double monthChangePct;
	
	@Column(name="threeMonthChangePct")
	private Double threeMonthChangePct;
	
	@Column(name="yearlyChangePct")
	private Double yearlyChangePct;
	
	@Column(name="volumeChangePct")
	private Double volumeChangePct;
	
	@Column(name="sma_50")
	private Double sma50;
	
	@Column(name="sma_100")
	private Double sma100;
	
	@Column(name="sma_200")
	private Double sma200;

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

	public Double getFiveDayPriceChange() {
		return fiveDayPriceChange;
	}

	public void setFiveDayPriceChange(Double fiveDayPriceChange) {
		this.fiveDayPriceChange = fiveDayPriceChange;
	}

	public Double getWeekChangePct() {
		return weekChangePct;
	}

	public void setWeekChangePct(Double weekChangePct) {
		this.weekChangePct = weekChangePct;
	}

	public Double getMonthChangePct() {
		return monthChangePct;
	}

	public void setMonthChangePct(Double monthChangePct) {
		this.monthChangePct = monthChangePct;
	}

	public Double getThreeMonthChangePct() {
		return threeMonthChangePct;
	}

	public void setThreeMonthChangePct(Double threeMonthChangePct) {
		this.threeMonthChangePct = threeMonthChangePct;
	}

	public Double getYearlyChangePct() {
		return yearlyChangePct;
	}

	public void setYearlyChangePct(Double yearlyChangePct) {
		this.yearlyChangePct = yearlyChangePct;
	}

	public Double getVolumeChangePct() {
		return volumeChangePct;
	}

	public void setVolumeChangePct(Double volumeChangePct) {
		this.volumeChangePct = volumeChangePct;
	}

	public Double getSma50() {
		return sma50;
	}

	public void setSma50(Double sma50) {
		this.sma50 = sma50;
	}

	public Double getSma100() {
		return sma100;
	}

	public void setSma100(Double sma100) {
		this.sma100 = sma100;
	}

	public Double getSma200() {
		return sma200;
	}

	public void setSma200(Double sma200) {
		this.sma200 = sma200;
	}
	
	
}
