package com.tutorial.apidemo.Springboot_tutorial.Service;

import com.tutorial.apidemo.Springboot_tutorial.Model.Customer;
import com.tutorial.apidemo.Springboot_tutorial.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetails implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository; //gọi thông tin user, lấy dữ liệu từ DB

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByUsername(username);
        String password = null;
        List<GrantedAuthority> authorities = null;
        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for username=" + username);
        }
        username = customers.get(0).getUsername(); //duy nhat trong DB
        password = customers.get(0).getPassword();
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));


        return new User(username, password, authorities);
    }
}
