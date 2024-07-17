package com.mandiri.service.impl;

import com.mandiri.dto.reponse.CustomerResponse;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.entity.Customer;
import com.mandiri.repository.CustomerRepository;
import com.mandiri.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest request){
        log.info("Received customer request: {}", request);
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setPhone(request.getPhone());
        customer.setUser(request.getUser());
        customer = customerRepository.save(customer);
        log.info("Saved Customer: {}", request);
        return convertToCustomerResponse(customer);
    }

    public List<CustomerResponse> getAllCustomer(){
        return customerRepository.findAll().stream().map(this::convertToCustomerResponse).toList();
    }

    public CustomerResponse updateCustomerById(CustomerRequest request){
        findByIdOrThrowNotFound(request.getId());
        Customer customer = customerRepository.saveAndFlush(
                Customer.builder()
                        .id(request.getId())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phone(request.getPhone())
                        .dateOfBirth(request.getDateOfBirth())
                        .build()
        );
        return convertToCustomerResponse(customer);
    }

    public CustomerResponse detailCustomer(String id){
       Customer customer =  findByIdOrThrowNotFound(id);
       return convertToCustomerResponse(customer);

    }

    public void deleteById(String id){
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    public Customer getById(String id){
        return findByIdOrThrowNotFound(id);

    }


    private CustomerResponse convertToCustomerResponse(com.mandiri.entity.Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .build();
    }

    private Customer findByIdOrThrowNotFound(String id){
        return customerRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }


}
