package com.ICINBANK.Response;

public class TransferResponse
{
    private boolean transferStatus;
    private String responseMessage;
    private long saccount;
    
    public boolean isTransferStatus() {
        return this.transferStatus;
    }
    
    public void setTransferStatus(final boolean transferStatus) {
        this.transferStatus = transferStatus;
    }
    
    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public long getSaccount() {
        return this.saccount;
    }
    
    public void setSaccount(final long saccount) {
        this.saccount = saccount;
    }
}