package com.MyMoviePlan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="genre_nms",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Genre {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique=true)
	private String name;
	
	private String description;
	
	 @Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false)
		@CreationTimestamp
		private Date created_at;
		
		@PrePersist
		private void onCreate() {
			created_at=new Date();
			
			}
	 
	
	Genre(){}
	
	Genre(String name, String description){
		
		this.name=name;
		this.description=description;
	}
	
	
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getDescription() {
		return description.toUpperCase();
	}

	public void setDescription(String description) {
		this.description = description.toUpperCase();
	}


}
