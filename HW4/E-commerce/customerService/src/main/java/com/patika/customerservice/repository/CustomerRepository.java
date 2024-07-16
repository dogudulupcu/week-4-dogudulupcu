package com.patika.customerservice.repository;

import com.patika.customerservice.entities.Customer;

import javax.swing.text.DefaultEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private List<Customer> customerList=new ArrayList<>();


    public List<Customer> getAll(){
        return customerList;
    }

    public void save(Customer customer){
        customerList.add(customer);
    }

    public Optional<Customer> findById(long customerId){
        return customerList.stream()
                .filter(customer -> customer.getCustomerId()==customerId)
                .findFirst();
    }


}
