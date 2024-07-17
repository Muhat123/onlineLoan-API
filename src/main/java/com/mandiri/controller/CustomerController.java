package com.mandiri.controller;

import com.mandiri.dto.reponse.CustomerResponse;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.entity.LoanType;
import com.mandiri.repository.CustomerRepository;
import com.mandiri.service.service.CustomerService;
import com.mandiri.service.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    private final LoanService loanService;
    private final CustomerRepository customerRepository;


    @PostMapping
    public ResponseEntity <CustomerResponse> postCustomer(@RequestBody CustomerRequest request){
        log.info("Received request in controller: {}", request);
        CustomerResponse customerResponse = customerService.saveCustomer(request);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping("/loan")
    public ResponseEntity<LoanType> addLoan(@RequestBody LoanType loan){
        return ResponseEntity.ok().body(loanService.addLoan(loan));
    }


    @GetMapping("/admin/allcustomer")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        return ResponseEntity.ok().body(customerService.getAllCustomer());
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request){
        CustomerResponse response = customerService.updateCustomerById(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok().body(customerService.detailCustomer(id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        customerService.deleteById(id);
        return ResponseEntity.ok("Successfully delete by id");
    }

    @GetMapping
    public ResponseEntity<LoanType> getAllLoan(){
        return ResponseEntity.ok().body(loanService.getAllLoan());
    }

    @PutMapping("/admin/loan")
    public ResponseEntity<LoanType> updateAllLoan(@RequestBody LoanType loanType){
        LoanType loan = loanService.updateLoan(loanType);
        return ResponseEntity.ok().body(loan);
    }

    @DeleteMapping("/admin/loan/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable String id){
        loanService.deleteById(id);
        return ResponseEntity.ok("Successfully delete loan by id");
    }


}
