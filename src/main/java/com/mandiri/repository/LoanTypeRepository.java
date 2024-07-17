package com.mandiri.repository;

import com.mandiri.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType,String> {

}
