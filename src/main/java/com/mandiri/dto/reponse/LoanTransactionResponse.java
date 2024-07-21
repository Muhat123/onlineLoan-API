package com.mandiri.dto.reponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String id;
    private String customerId;
    private String loanTypeId;
    private String instalmentTypeId;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private String approvalStatus;
    private List<LoanTransactionDetailResponse> loanTransactionDetails;
    private Long createdAt;
    private Long updatedAt;
    private String midtrans;


}