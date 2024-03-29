package com.MyMoviePlan.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	   
	    
	    @NotBlank
	    @Size(max = 20)
	    private String username;
	    
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    @NotBlank
	    @Size(max = 120)
	    private String password;
	    
	    
	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	    
	    private Set<Role> roles = new HashSet<>();
	    
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false)
		@CreationTimestamp
		private Date created_at;
		
		@PrePersist
		private void onCreate() {
			created_at=new Date();
			
			}
	    

		public User() {
	    }

	    public User(String username, String email, String password) {
	      this.username = username;
	      this.email = email;
	      this.password = password;
	    	    
	    }
	    
	    
	    
		

		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
		
		public Set<Role> getRoles() {
				return roles;
			}

		 public void setRoles(Set<Role> roles) {
				this.roles = roles;
			}
	 

}
