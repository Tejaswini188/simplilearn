package com.MyMoviePlan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MyMoviePlan.model.User;
import com.MyMoviePlan.repository.UserRepository;



@Service
public class ChangePasswordService  {
	
	
	User user;
	

    @Autowired
    UserRepository userRepository;
    
    
	
	public String changePassword(String userName, String newPassword){
		
		Optional<User> userData = userRepository.findByUsername(userName);
		
				
		if (userData.isPresent()) {
			user=userData.get();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        
			user.setPassword(encodedPassword);
			userRepository.save(user);
			
			return "Your password successfully updated.";
		}
		else {
			return "Invalid Username. Password update not successful.";
		}
		
	}
		
	

}
