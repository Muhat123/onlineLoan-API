package com.mandiri.service.impl;

import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.*;
import com.mandiri.repository.LoanTransactionDetailRepository;
import com.mandiri.repository.LoanTransactionRepository;
import com.mandiri.service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanTransactionImpl implements LoanTransactionService {

    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanService loanService;
    private final CustomerService service;
    private final InstalmentService instalmentService;
    private final RoleService roleService;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;

    public LoanTransaction addLoanTransaction_oneMonth(LoanTransactionRequest request) {
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.ONE_MONTH);

        LoanTransaction transaction = LoanTransaction.builder()
                .customer(customer)
                .loanType(loanType)
                .instalmentType(instalmentType)
                .nominal(request.getNominal())
                .approvedAt(request.getApprovedAt())
                .approvedBy(roleService.getOrSave(Role.ERole.ROLE_ADMIN).getName().toString())
                .approvalStatus(LoanTransaction.ApprovalStatus.APPROVED)
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();

        List<LoanTransactionDetail> transactionDetails = request.getTransactionDetails().stream()
                .map(td -> LoanTransactionDetail.builder()
                        .transactionDate(td.getTransactionDate())
                        .nominal(td.getNominal())
                        .loanStatus(LoanTransactionDetail.LoanStatus.UNPAID)
                        .loanTransaction(transaction)
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build())
                .collect(Collectors.toList());

        transaction.setLoanTransactionDetails(transactionDetails);

        return loanTransactionRepository.save(transaction);
    }
}

