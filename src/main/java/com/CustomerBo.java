package com;

import com.dao.CustomerDao;
import com.model.Customer;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by vijay on 2/18/17.
 */
public class CustomerBo {
    public String getMessage() {
        return message;
    }

    private CustomerDao customerDao;
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
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

    @Transactional
    public void insert(Customer customer){
        customerDao.insert(customer);
    }



}
