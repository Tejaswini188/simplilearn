package com.ICINBANK.Response;

public class RegisterResponse
{
    private boolean registrationStatus;
    private String responseMessage;
    private String username;
    
    public boolean getRegistrationStatus() {
        return this.registrationStatus;
    }
    
    public void setRegistrationStatus(final boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
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
