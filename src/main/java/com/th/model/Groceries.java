package com.th.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Groceries {
	
	@Id
	private int proid;
	private String categories;
	private String proname;
	private String quantity;
	private int price;
	
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Groceries [proid=" + proid + ", categories=" + categories + ", proname=" + proname + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	public Groceries(int proid, String categories, String proname, String quantity, int price, String usebefore) {
		super();
		this.proid = proid;
		this.categories = categories;
		this.proname = proname;
		this.quantity = quantity;
		this.price = price;
		
	}
	public Groceries() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}