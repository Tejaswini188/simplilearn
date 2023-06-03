package com.MyMoviePlan.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.MyMoviePlan.model.User;
 
public class SignupRequest {
	
/*	
  private UserData userdata;
  private Set<String> role;

public UserData getUserdata() {
	return userdata;
}

public void setUserdata(UserData userdata) {
	this.userdata = userdata;
}



public Set<String> getRole() {
    return this.role;
  }
  
  public void setRole(Set<String> role) {
    this.role = role;
  }
  
 */

	@NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
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
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private User user;
 



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
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
