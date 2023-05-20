package com.ICINBANK.Service;

import com.ICINBANK.Entity.UserDisplay;
import com.ICINBANK.Entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import com.ICINBANK.Response.UpdateResponse;
import com.ICINBANK.Details.UpdateDetails;
import com.ICINBANK.Repository.UserDisplayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService
{
    @Autowired
    private UserRepository dao;
    @Autowired
    private UserDisplayRepository userDisplayDao;
    
    public UpdateResponse updateUser(final UpdateDetails user) {
        boolean flag = true;
        final UpdateResponse response = new UpdateResponse();
        String message = "Update successful";
        try {
            int counter = 0;
            final User u = this.dao.findByUsername(user.getUsername());
            if (user.getAddress().length() != 0) {
                ++counter;
                u.setAddress(user.getAddress());
            }
            if (user.getPassword().length() != 0 && user.getNewpassword().length() != 0) {
                ++counter;
                final String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
                u.setPassword(hashedPassword);
            }
            if (user.getEmail().length() != 0) {
                ++counter;
                u.setEmail(user.getEmail());
            }
            if (user.getPhone() != 0L) {
                ++counter;
                u.setPhone(user.getPhone());
            }
            System.out.println(counter);
            if (counter > 0) {
                this.dao.save((Object)u);
            }
            else {
                flag = false;
                message = "Please enter some information to update";
            }
        }
        catch (Exception e) {
            flag = false;
            response.setMessage("Update unsuccesful");
        }
        response.setMessage(message);
        response.setFlag(flag);
        return response;
    }
    
    public User getUser(final String username) {
        return this.dao.findByUsername(username);
    }
    
    public UserDisplay userDisplay(final String username) {
        final UserDisplay user = this.userDisplayDao.getCurrentUser(username);
        return user;
    }
}