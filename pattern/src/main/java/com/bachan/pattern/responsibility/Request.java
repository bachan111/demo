package com.bachan.pattern.responsibility;

public class Request {
    private String requestValue;

    public Request(String requestValue) {
        this.requestValue = requestValue;
    }

    public String getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(String requestValue) {
        this.requestValue = requestValue;
    }
}
