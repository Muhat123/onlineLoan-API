package com.mandiri.repository;

import com.mandiri.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction,String> {

}
