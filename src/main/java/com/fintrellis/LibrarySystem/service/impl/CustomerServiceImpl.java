package com.fintrellis.LibrarySystem.service.impl;

import com.fintrellis.LibrarySystem.Exceptions.NotFoundException;
import com.fintrellis.LibrarySystem.dto.CustomerDTO;
import com.fintrellis.LibrarySystem.dto.UserDTO;
import com.fintrellis.LibrarySystem.model.Book;
import com.fintrellis.LibrarySystem.model.Customer;
import com.fintrellis.LibrarySystem.repository.BookRepository;
import com.fintrellis.LibrarySystem.repository.CustomerRepository;
import com.fintrellis.LibrarySystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                customer -> new CustomerDTO(customer.getId(),customer.getEmail(),customer.getUsername(),
                        customer.getBook() != null ? customer.getBook().getId(): null)).collect(Collectors.toList()
        );
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow( ()-> new NotFoundException("Customer with ID " + id + " not found"));
        return new CustomerDTO(customer.getId(), customer.getEmail(),customer.getUsername()
                ,customer.getBook() != null? customer.getBook().getId():null);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getEmail(),customerDTO.getUsername(),customerDTO.getPassword());
        customerRepository.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }

    @Override
    public boolean assignBook(long customer_id, long book_id) {
        Book book = bookRepository.findById(book_id).orElseThrow(()-> new NotFoundException("Book with ID " + book_id + " not found"));
        Customer customer = customerRepository.findById(customer_id).orElseThrow(()-> new NotFoundException("Customer with ID " + customer_id + " not found"));
        if(book.equals(customer.getBook()))
            return false;
        customer.setBook(book);
        customerRepository.save(customer);
        return true;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer with ID " + id + " not found"));
        customerRepository.delete(customer);
    }
}
