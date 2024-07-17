package com.mandiri.dto.request;

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
    private List<LoanTransactionDetail> transactionDetails = new ArrayList<>(); //
}
