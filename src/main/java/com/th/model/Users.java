package com.th.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@Id
	private String useremail;
	private String password;
	private int phoneno;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}
	
	
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [userid=" + userid + ", useremail=" + useremail + ", password=" + password + ", phoneno="
				+ phoneno + "]";
	}
	public Users(int userid, String useremail, String password, int phoneno) {
		super();
		this.userid = userid;
		this.useremail = useremail;
		this.password = password;
		this.phoneno = phoneno;
	}
	
	
	
	
	

}
