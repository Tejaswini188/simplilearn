package com.MyMoviePlan.response;

public class ReceiptResponse {
	private String firstName;
	private String lastName;
	private String description;
	private float subtotal;
	private float tax;
	private float total;
	private String email;
	private String payerid;
	private String paymentid;
	
	public ReceiptResponse(String firstName,String lastName, String description,String subtotal, 
			String tax, String total, String email, String payerid, String paymentid) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.description=description;
		this.subtotal = Float.parseFloat(subtotal);
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
		this.email=email;
		this.payerid=payerid;
		this.paymentid=paymentid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubtotal() {
		return String.format("%.2f", subtotal);
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}


	public String getTax() {
		return String.format("%.2f", tax);
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return String.format("%.2f", total);
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayerid() {
		return payerid;
	}

	public void setPayerid(String payerid) {
		this.payerid = payerid;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	
	
	
}
