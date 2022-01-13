package com.th.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Userscartitems1 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemid;
	private int quantity;
	private float totalprice;
	@ManyToOne
	@JoinColumn(name = "useremail", nullable = true)
	private Users user;
	@OneToOne
	@JoinColumn(name = "proid", nullable = true)
	public Groceries grocerie;

	@Override
	public String toString() {
		return "Userscartitems [itemid=" + itemid + ", quantity=" + quantity + ", totalprice=" + totalprice + ", user="
				+ user + ", groceries=" + grocerie + "]";
	}

	public Userscartitems1() {
		super();
// TODO Auto-generated constructor stub
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Groceries getGroceries() {
		return grocerie;
	}

	public void setGroceries(Groceries groceries) {
		this.grocerie = groceries;
	}

	public Userscartitems1(int itemid, int quantity, float totalprice, Users user, Groceries groceries) {
		super();
		this.itemid = itemid;
		this.quantity = quantity;
		this.totalprice = totalprice;
		this.user = user;
		this.grocerie = groceries;
	}
}
