package com.ICINBANK.Response;

public class ChequeResponse
{
    private boolean status;
    private String responseMessage;
    private long account;
    
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(final boolean status) {
        this.status = status;
    }
    
    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public long getAccount() {
        return this.account;
    }
    
    public void setAccount(final long account) {
        this.account = account;
    }
}