package com.th.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	private int adminid;
	private String password;
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", password=" + password + "]";
	}
	public Admin(int adminId, String password) {
		super();
		this.adminid = adminId;
		this.password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}   
	
	
	
}
