package com.th.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyOrder {

	@Id
	private String orderid;
	private String address;
	private int piadamount;
	private Date orderdate;
	private String useremail;

	public MyOrder() {
		super();
// TODO Auto-generated constructor stub
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public MyOrder(String orderid, String address, int piadamount, Date orderdate, String useremail) {
		super();
		this.orderid = orderid;
		this.address = address;
		this.piadamount = piadamount;
		this.orderdate = orderdate;
		this.useremail = useremail;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPiadamount() {
		return piadamount;
	}

	public void setPiadamount(int piadamount) {
		this.piadamount = piadamount;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "MyOrder [orderid=" + orderid + ", address=" + address + ", piadamount=" + piadamount + ", orderdate="
				+ orderdate + ", useremail=" + useremail + "]";
	}

}
