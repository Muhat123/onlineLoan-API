package com.mandiri.service.impl;

import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.Customer;
import com.mandiri.entity.InstalmentType;
import com.mandiri.entity.LoanTransaction;
import com.mandiri.entity.LoanType;
import com.mandiri.repository.CustomerRepository;
import com.mandiri.repository.LoanTransactionRepository;
import com.mandiri.repository.LoanTypeRepository;
import com.mandiri.service.service.CustomerService;
import com.mandiri.service.service.InstalmentService;
import com.mandiri.service.service.LoanService;
import com.mandiri.service.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanTransactionImpl implements LoanTransactionService {

    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanService loanService;
    private final CustomerService service;
    private final InstalmentService instalmentService;

    public LoanTransaction addLoanTransaction(LoanTransactionRequest request){
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.ONE_MONTH);
        LoanTransaction transaction1 = LoanTransaction.builder().build();


        return loanTransactionRepository.save(transaction1);
    }
}
