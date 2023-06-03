package com.MyMoviePlan.request;

import java.util.HashSet;
import java.util.Set;

import com.MyMoviePlan.model.Role;


public class UserRequest {

	
	private String firstname;
    
    private String lastname;
    private String email;
      
    private Set<Role> roles = new HashSet<>();
    	
    private String address;
      
    private String city;
      
    private String country;
    
    
    public UserRequest(){}
    
    public UserRequest(String firstname,String lastname,String email,String address,String city,
    		String country,Set<Role> roles){
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.email=email;
    	this.address=address;
    	this.city=city;
    	this.country=country;
    	this.roles=roles;
    	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
