package com.mandiri.service.service;

import com.mandiri.entity.LoanType;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    LoanType addLoan(LoanType loan);
    List<LoanType> getAllLoan();
    LoanType updateLoan(LoanType loan);
    void deleteById(String id);
    LoanType getLoanTypeById(String id);

}
