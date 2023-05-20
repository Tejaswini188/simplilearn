package com.ICINBANK.Service;

import com.ICINBANK.Entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import com.ICINBANK.Response.LoginResponse;
import com.ICINBANK.Details.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    @Autowired
    private UserRepository dao;
    
    public LoginResponse customerLogin(final LoginDetails login) {
        final LoginResponse response = new LoginResponse();
        boolean flag = true;
        String message = "Login succesfull";
        User user = null;
        final String hashedPassword = DigestUtils.sha256Hex(login.getPassword());
        try {
            user = this.dao.findByUsername(login.getUsername());
            if (user.getStatus()) {
                flag = false;
                message = "Dear Mr/Ms." + user.getFname() + " your account has been blocked for security reasons.";
            }
            if (!user.getAuthorizationStatus()) {
                flag = false;
                message = "Dear Mr/Ms." + user.getFname() + " your account has not been activated yet";
            }
            if (!hashedPassword.equals(user.getPassword())) {
                flag = false;
                message = "Username or password is incorrect";
            }
        }
        catch (Exception e) {
            flag = false;
            message = "Username or password is incorrect";
        }
        response.setLoginStatus(flag);
        response.setResponseMessage(message);
        try {
            response.setUsername(user.getUsername());
        }
        catch (Exception e) {
            response.setUsername(login.getUsername());
        }
        return response;
    }
}
