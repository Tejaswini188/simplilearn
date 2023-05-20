package com.ICINBANK.Response;

public class DepositResponse
{
    private boolean depositStatus;
    private String responseMessage;
    private long account;
    
    public boolean isDepositStatus() {
        return this.depositStatus;
    }
    
    public void setDepositStatus(final boolean depositStatus) {
        this.depositStatus = depositStatus;
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
