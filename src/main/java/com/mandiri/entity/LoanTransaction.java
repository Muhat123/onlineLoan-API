package com.mandiri.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "m_loantransaction")
@Builder
public class LoanTransaction {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private LoanType loanType;

    @ManyToOne
    private InstalmentType instalmentType;

    @ManyToOne
    private Customer customer;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private ApprovalStatus approvalStatus;

    @OneToMany (mappedBy = "loanTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();
    private Long createdAt;
    private Long updatedAt;


    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }
}



