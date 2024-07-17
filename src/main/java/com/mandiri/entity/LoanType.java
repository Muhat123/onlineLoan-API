package com.mandiri.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_type")
@Builder
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private Double maxLoan;
    @OneToOne
    private Customer customer;

}
