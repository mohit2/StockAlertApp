package com.shanti.StockAlertApp.Model.QuandleModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="stock_historical_price")
public class StockPrice {
	
	@EmbeddedId
	private CompositeKey key;
	@Column(name="open")
	private Double open;
	@Column(name="high")
	private Double high;
	@Column(name="low")
	private Double low;
	@Column(name="last")
	private Double last;
	@Column(name="close")
	private Double close;
	@Column(name="tradeQuantity")
	private Double tradeQuantity;
	@Column(name="Turnover")
	private Double turnOver;
	public CompositeKey getKey() {
		return key;
	}
	public void setKey(CompositeKey key) {
		this.key = key;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getLast() {
		return last;
	}
	public void setLast(Double last) {
		this.last = last;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getTradeQuantity() {
		return tradeQuantity;
	}
	public void setTradeQuantity(Double tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}
	public Double getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(Double turnOver) {
		this.turnOver = turnOver;
	}
	
}
