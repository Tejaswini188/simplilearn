package com.ICINBANK.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ICINBANK.Details.UpdateDetails;
import com.ICINBANK.Entity.User;
import com.ICINBANK.Entity.UserDisplay;
import com.ICINBANK.response.UpdateResponse;
import com.ICINBANK.service.ProfileService;

@RestController
//@CrossOrigin
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

	@Autowired
	private ProfileService pservice;
	
	@PutMapping("/profile/update")
	public UpdateResponse updateUser(@RequestBody UpdateDetails user) {
		// TODO Auto-generated method stub
		return pservice.updateUser(user);
	}

	@GetMapping("/profile/{username}")
	public User getUser(@PathVariable("username") String username) {
		// TODO Auto-generated method stub
		return pservice.getUser(username);
	}
	
	@GetMapping("home/{username}")
	public UserDisplay userDisplay(@PathVariable("username")String username) {
		return pservice.userDisplay(username);
	}

}
