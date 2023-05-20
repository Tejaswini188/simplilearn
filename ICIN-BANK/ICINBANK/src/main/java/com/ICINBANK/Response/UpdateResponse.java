package com.ICINBANK.Response;

public class UpdateResponse
{
    public boolean flag;
    public String message;
    
    public boolean isFlag() {
        return this.flag;
    }
    
    public void setFlag(final boolean flag) {
        this.flag = flag;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
}