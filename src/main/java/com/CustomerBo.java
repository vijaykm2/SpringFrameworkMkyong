package com;

/**
 * Created by vijay on 2/18/17.
 */
public class CustomerBo {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public void printMsg() {
        System.out.println("Customer Bo" + this.getMessage());
    }
}
