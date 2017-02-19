package com.dao;

import com.model.Customer;

/**
 * Created by vijay on 2/19/17.
 */
public interface CustomerDao {
    public void insert(Customer customer);
    public Customer findCustomerById(String name);
}
