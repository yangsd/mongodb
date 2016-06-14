package com.demo.dao;


import com.demo.bean.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by sdyang on 2016/6/14.
 */
public interface CustomerDao extends MongoRepository<Customer, String>{

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
