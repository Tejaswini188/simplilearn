package com.ICINBANK.Response;

public class LoginResponse
{
    private boolean loginStatus;
    private String responseMessage;
    private String username;
    
    public boolean getLoginStatus() {
        return this.loginStatus;
    }
    
    public void setLoginStatus(final boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
}
