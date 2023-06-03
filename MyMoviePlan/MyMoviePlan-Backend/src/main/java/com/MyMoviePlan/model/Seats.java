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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "seat_nms",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seatnumber","showId"})
})
public class Seats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int seatnumber;
	private int customerId;
	private int showId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@CreationTimestamp
	private Date created_at;
	
	@PrePersist
	private void onCreate() {
		created_at=new Date();
		
		}
	
	  Seats(){}
	
	  public Seats(int customerId, int seatnumber,int showId) {
	     	this.customerId=customerId;
			this.seatnumber=seatnumber;
			this.showId=showId;
	    }
	  
	  
	  
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public Date getCreated_at() {
	return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(int seatnumber) {
		this.seatnumber = seatnumber;
	}	
	
	
}
