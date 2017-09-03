package com.shanti.StockAlertApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Screener_permissions")
public class ScreenerPermissions {
	
	@Id
	@Column(name="Email")
	private String email;
	
	@Column(name="permission_id")
	private String permId;
	
	@Column(name="role")
	private String role;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPermId() {
		return permId;
	}
	public void setPermId(String permId) {
		this.permId = permId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
