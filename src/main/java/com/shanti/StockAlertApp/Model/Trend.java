package com.shanti.StockAlertApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trend")
public class Trend {

	@Id
	@Column(name="Symbol")
	private String symbol;
	
	@Column(name="5_Day_Price_Change")
	private Double fiveDayPriceChange;
	
	@Column(name="10_Day_Price_Change")
	private Double tenDayPriceChange;
	
	@Column(name="30_Day_Price_Change")
	private Double thirtyDayPriceChange;
	
	@Column(name="5_Day_Volume_Change")
	private Double fiveDayVolumeChange;
	
	@Column(name="10_Day_Volume_Change")
	private Double tenDayVolumeChange;
	
	@Column(name="30_Day_Volume_Change")
	private Double thirtyDayVolumeChange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getFiveDayPriceChange() {
		return fiveDayPriceChange;
	}

	public void setFiveDayPriceChange(Double fiveDayPriceChange) {
		this.fiveDayPriceChange = fiveDayPriceChange;
	}

	public Double getTenDayPriceChange() {
		return tenDayPriceChange;
	}

	public void setTenDayPriceChange(Double tenDayPriceChange) {
		this.tenDayPriceChange = tenDayPriceChange;
	}

	public Double getThirtyDayPriceChange() {
		return thirtyDayPriceChange;
	}

	public void setThirtyDayPriceChange(Double thirtyDayPriceChange) {
		this.thirtyDayPriceChange = thirtyDayPriceChange;
	}

	public Double getFiveDayVolumeChange() {
		return fiveDayVolumeChange;
	}

	public void setFiveDayVolumeChange(Double fiveDayVolumeChange) {
		this.fiveDayVolumeChange = fiveDayVolumeChange;
	}

	public Double getTenDayVolumeChange() {
		return tenDayVolumeChange;
	}

	public void setTenDayVolumeChange(Double tenDayVolumeChange) {
		this.tenDayVolumeChange = tenDayVolumeChange;
	}

	public Double getThirtyDayVolumeChange() {
		return thirtyDayVolumeChange;
	}

	public void setThirtyDayVolumeChange(Double thirtyDayVolumeChange) {
		this.thirtyDayVolumeChange = thirtyDayVolumeChange;
	}
	
	
}
