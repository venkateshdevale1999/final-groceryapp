package com.th.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	
	
	private String username;
	@Id
	private String useremail;
	private String password;
	private String phoneno;
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [userid=" + username + ", useremail=" + useremail + ", password=" + password + ", phoneno="
				+ phoneno + "]";
	}
	public Users(String username, String useremail, String password, String phoneno) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.password = password;
		this.phoneno = phoneno;
	}
	
	
	
	
	

}
