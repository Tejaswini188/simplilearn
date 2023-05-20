package com.ICINBANK.Service;

import org.apache.commons.codec.digest.DigestUtils;
import com.ICINBANK.Response.RegisterResponse;
import com.ICINBANK.Entity.User;
import com.ICINBANK.Service.SaccountService;
import com.ICINBANK.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.UserRepository;
import org.springframework.stereotype.Service;
import com.ICINBANK.Service.RegistrationService;

@Service
public class RegistrationService
{
    @Autowired
    private UserRepository dao;
    @Autowired
    private AccountService service;
    @Autowired
    private SaccountService sservice;
    
    public RegisterResponse createAccount(final User user) {
        final RegisterResponse response = new RegisterResponse();
        boolean flag = true;
        String message = "Registration Succesful";
        if (this.EmailAlreadyExists(user.getEmail())) {
            message = "Email already Exists";
            flag = false;
        }
        if (this.PhoneAlreadyExists(user.getPhone())) {
            message = "Phone number already Exists";
            flag = false;
        }
        if (this.usernameAlreadyExists(user.getUsername())) {
            message = "Username already Exists";
            flag = false;
        }
        if (flag) {
            final String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
            user.setPassword(hashedPassword);
            this.dao.save((Object)user);
        }
        response.setRegistrationStatus(flag);
        response.setResponseMessage(message);
        response.setUsername(user.getUsername());
        return response;
    }
    
    public boolean usernameAlreadyExists(final String username) {
        try {
            final User u = this.dao.findByUsername(username);
            System.out.println(u.toString());
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean EmailAlreadyExists(final String email) {
        try {
            final User u = this.dao.findByEmail(email);
            System.out.println(u.toString());
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean PhoneAlreadyExists(final long l) {
        try {
            final User u = this.dao.findByPhone(l);
            System.out.println(u.toString());
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
