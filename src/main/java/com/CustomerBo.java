package com;

import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;

/**
 * Created by vijay on 2/18/17.
 */
public class CustomerBo {
    public String getMessage() {
        return message;
    }

    @Required
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public void printMsg() {
        System.out.println("Customer Bo" + this.getMessage());
    }

    @PostConstruct
    public void initIt(){
        System.out.println("Customer bean initialized!! "+ this.getMessage());
    }
}
