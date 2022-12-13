package com.project.customerservice.service;

import com.project.customerservice.entity.Customer;

public interface CustomerService {
    public Customer getCustomer(int id);
    public boolean updateCustomer(Customer customer, int id);
}
