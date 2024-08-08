package com.tutorial.apidemo.Springboot_tutorial.Service;

import com.tutorial.apidemo.Springboot_tutorial.Model.Customer;
import com.tutorial.apidemo.Springboot_tutorial.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepository customerRepository; //lưu xuống DB xong encode
    public Customer createCustomer(Customer customer) {
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        return customerRepository.save(customer);
    }
}
