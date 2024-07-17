package com.mandiri.service.service;

import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.LoanTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTransactionService {
    LoanTransaction addLoanTransaction_oneMonth(LoanTransactionRequest request);
}
