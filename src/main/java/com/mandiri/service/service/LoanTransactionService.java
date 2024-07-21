package com.mandiri.service.service;

import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.LoanTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanTransactionService {
    LoanTransaction addLoanTransaction_oneMonth(LoanTransactionRequest request);
    LoanTransaction addLoanTransaction_threeMonth(LoanTransactionRequest request);
    LoanTransaction addLoanTransaction_sixthMonth(LoanTransactionRequest request);
    LoanTransaction addLoanTransaction_NineMonth(LoanTransactionRequest request);
    LoanTransaction addLoanTransaction_TwelveMonth(LoanTransactionRequest request);
    List<LoanTransaction> getAllLoanTransactions();
    LoanTransaction getLoanById(String id);
    void deleteById(String id);
    LoanTransaction updateBalanceLoan(LoanTransactionRequest request);
    String processLoanPayment(LoanTransactionRequest request);
    LoanTransaction changeLoanBalance(LoanTransactionRequest request);
}
