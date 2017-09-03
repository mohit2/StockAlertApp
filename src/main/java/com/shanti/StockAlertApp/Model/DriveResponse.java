package com.shanti.StockAlertApp.Model;

import java.util.List;

public class DriveResponse {
	
	private String kind;
	private List<Permissions> permissions;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public List<Permissions> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permissions> permissions) {
		this.permissions = permissions;
	}
	
	

}
