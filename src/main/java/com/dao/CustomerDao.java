package com.dao;

import com.model.Customer;

import java.util.List;

/**
 * Created by vijay on 2/19/17.
 */
public interface CustomerDao {
    public void insert(Customer customer);
    public Customer findCustomerById(String name);

    void performBatchUpdate(List<Customer> customers);
}
