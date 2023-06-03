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
@Table(name = "payment_nms")
public class PaymentPOJO {
	
	    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	    
	    private String firstname;
	    	       
	    private String lastname;
	    
	    private String description;
	    
	    private String email;
	    
	    private String amount;
	    	   
	    private String tax;
	    
	    private String total;
	    
	    private String payer;
	    
	    private String payments;
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false)
		@CreationTimestamp
		private Date created_at;
		
		@PrePersist
		private void onCreate() {
			created_at=new Date();
			
			}
	    


		public PaymentPOJO() {
	    }

	    public PaymentPOJO(String firstname,String lastname, String description, String amount,String tax,String total, String payer, String payment,
	    		String email) {
	      this.firstname=firstname;
	      this.lastname = lastname;
	      this.description = description;
	      this.email=email;
	      this.amount = amount;
	      this.tax=tax;
	      this.total=total;
	      this.payer=payer;
	      this.payments=payment; 	    	    
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		

		public String getFirstname() {
			return firstname;
		}


		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}



		public Date getCreated_at() {
			return created_at;
		}


		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}



		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		

		
       public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getTax() {
			return tax;
		}

		public void setTax(String tax) {
			this.tax = tax;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		/*
		public String getAmount() {
			return String.format("%.2f", amount);
		}

		public void setAmount(String amount) {
			this.amount = this.tax=Float.parseFloat(amount);;
		}

		public String getTax() {
			return String.format("%.2f", tax);
		}

		public void setTax(String tax) {
			
			this.tax=Float.parseFloat(tax);
		}

		public String getTotal() {
			return String.format("%.2f", total);
		}

		public void setTotal(String total) {
			this.total = this.tax=Float.parseFloat(total);;
		}
*/
		public String getPayer() {
			return payer;
		}

		public void setPayer(String payer) {
			this.payer = payer;
		}

		public String getPayments() {
			return payments;
		}

		public void setPayments(String payments) {
			this.payments = payments;
		}

	
	    
	    


}
