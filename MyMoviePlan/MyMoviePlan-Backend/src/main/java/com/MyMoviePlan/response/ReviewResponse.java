package com.MyMoviePlan.response;

public class ReviewResponse {
	private String firstName;
	private String lastName;
	private String email;
	private String paymentId;
	private String payerId;
	private String description;
	private float subtotal;
	private float tax;
	private float total;
	
	public ReviewResponse(String firstName,String lastName,String email,String paymentId,String payerId,String description, String subtotal, 
			 String tax, String total) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.paymentId=paymentId;
		this.payerId=payerId;
		this.description=description;
		this.subtotal = Float.parseFloat(subtotal);
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
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
	
	
	
}
