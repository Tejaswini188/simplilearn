package com.MyMoviePlan.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "userdetails")
public class UserData {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    private String firstname;
	    
	    @NotBlank
	    @Size(max = 20)
	    private String lastname;
	    
		    
	    @NotBlank
	    @Size(max = 120)
	    private String address;
	    
	    @NotBlank
	    @Size(max = 120)
	    private String city;
	    
	    @NotBlank
	    @Size(max = 120)
	    private String country;
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false)
		@CreationTimestamp
		private Date created_at;
		
		@PrePersist
		private void onCreate() {
			created_at=new Date();
			
			}
	    
	    //private long user_id;
	    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id", referencedColumnName = "id")
	    public User user;
	    
	    

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public UserData() {
	    }

	    public UserData(String firstname,String lastname, String address,String city,String country,User user){
	      this.firstname = firstname;
	      this.lastname = lastname;
	      this.address = address;
	      this.city=city;
	      this.country=country;
	      this.user=user;
	     
	    }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}


		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	    
	    


}
