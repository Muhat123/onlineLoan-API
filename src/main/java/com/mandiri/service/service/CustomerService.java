package com.mandiri.service.service;

import com.mandiri.dto.reponse.CustomerResponse;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomerById(CustomerRequest request);
    CustomerResponse detailCustomer(String id);
    void deleteById(String id);
    Customer getById(String id);
}
