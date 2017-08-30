package com.shanti.StockAlertApp.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "premium_member")
public class PremiumMember {

	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gmail")
	private String gmail;
	
	@Column(name="phn_no")
	private String phnNo;
	
	@Column(name="watsapp")
	private String watsapp;
	
	@Column(name="subscription_date")
	private Date subcriptionDate;
	
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@Column(name="active")
	private String active;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPhnNo() {
		return phnNo;
	}

	public void setPhnNo(String phnNo) {
		this.phnNo = phnNo;
	}

	public String getWatsapp() {
		return watsapp;
	}

	public void setWatsapp(String watsapp) {
		this.watsapp = watsapp;
	}

	public Date getSubcriptionDate() {
		return subcriptionDate;
	}

	public void setSubcriptionDate(Date subcriptionDate) {
		this.subcriptionDate = subcriptionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}
