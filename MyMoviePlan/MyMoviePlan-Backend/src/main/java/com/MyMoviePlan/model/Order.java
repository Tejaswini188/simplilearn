package com.MyMoviePlan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "order_nms")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productName;
	private float price;
	private float subtotal;//amount, price
	private float shipping;
	private float tax;
	private float total;
	private String seatnumber;
	private int productId;
	private int quantity;
	private int customerId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@CreationTimestamp
	private Date created_at;
	
	@PrePersist
	private void onCreate() {
		created_at=new Date();
		
		}
	
	  Order(){}
	
	  public Order(int productId, String productName, int quantity, String subtotal,String shipping, String tax,
			  String total, int customerId, int price, String seatnumber) {
	        this.productId = productId;
	        this.productName = productName;
	        this.quantity = quantity;
	        this.subtotal = Float.parseFloat(subtotal);
			this.shipping = Float.parseFloat(shipping);
			this.tax = Float.parseFloat(tax);
			this.total = Float.parseFloat(total);
			this.customerId=customerId;
			this.price=price;
			this.seatnumber=seatnumber;
	    }
	  
	  
	  
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	


	public String getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public Date getCreated_at() {
	return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/*public float getSubtotal() {
		return subtotal;
	}*/
	
	public String getSubtotal() {
		return String.format("%.2f", subtotal);
	}

	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}
	
	public String getTotal() {
		return String.format("%.2f", total);
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	/*public float getShipping() {
		return shipping;
	}*/
	public void setShipping(float shipping) {
		this.shipping = shipping;
	}
	/*public float getTax() {
		return tax;
	}*/
	public void setTax(float tax) {
		this.tax = tax;
	}
	/*public float getTotal() {
		return total;
	}*/
	public void setTotal(float total) {
		this.total = total;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	
	
	
}
