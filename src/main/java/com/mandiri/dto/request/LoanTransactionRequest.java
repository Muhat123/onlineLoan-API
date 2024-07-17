package com.mandiri.dto.request;

import com.mandiri.entity.LoanTransaction;
import com.mandiri.entity.LoanTransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanTransactionRequest {
    private String customerId;
    private String loanTypeId;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private LoanTransaction.ApprovalStatus approvalStatus;
    private List<LoanTransactionDetail> transactionDetails = new ArrayList<>(); //
    private Long createdAt;
    private Long updatedAt;
}
