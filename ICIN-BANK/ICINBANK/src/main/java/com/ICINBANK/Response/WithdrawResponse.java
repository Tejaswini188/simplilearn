package com.ICINBANK.Response;

public class WithdrawResponse
{
    private boolean withdrawStatus;
    private String responseMessage;
    private long account;
    
    public boolean isWithdrawStatus() {
        return this.withdrawStatus;
    }
    
    public void setWithdrawStatus(final boolean withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
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
