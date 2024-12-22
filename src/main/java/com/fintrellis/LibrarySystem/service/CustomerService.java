package com.fintrellis.LibrarySystem.service;

import com.fintrellis.LibrarySystem.dto.CustomerDTO;
import com.fintrellis.LibrarySystem.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    boolean assignBook(long customer_id , long book_id);
    void deleteCustomer(Long id);
}
