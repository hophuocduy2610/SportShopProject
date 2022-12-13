package com.project.customerservice.service;

import java.util.Optional;
import com.project.customerservice.entity.Customer;
import com.project.customerservice.repository.CustomerRepo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer getCustomer(int id) {
        return customerRepo.getCustomerByID(id);
    }

	@Override
	public boolean updateCustomer(Customer customer, int id) {
		try {
//			customerRepo.updateCustomerInformation(customer.getCustomerName(), customer.getEmail(), customer.getSdt(), customer.getId());
			Optional<Customer> customerTemp = customerRepo.findById(id);
			if(customerTemp.isPresent()) {
				Customer _customer = customerTemp.get();
				_customer.setCustomerName(customer.getCustomerName());
				_customer.setEmail(customer.getEmail());
				_customer.setSdt(customer.getSdt());
				customerRepo.save(_customer);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
