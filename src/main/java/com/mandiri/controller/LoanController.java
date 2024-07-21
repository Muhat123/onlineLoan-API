package com.mandiri.controller;

import com.mandiri.dto.reponse.LoanTransactionDetailResponse;
import com.mandiri.dto.reponse.LoanTransactionResponse;
import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.LoanTransaction;
import com.mandiri.entity.LoanTransactionDetail;
import com.mandiri.entity.LoanType;
import com.mandiri.entity.Role;
import com.mandiri.service.service.LoanService;
import com.mandiri.service.service.LoanTransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Authorization")
public class LoanController {
    private final LoanService loanService;
    private final LoanTransactionService loanTransactionService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @PostMapping("/admin/addloantype")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<LoanType> addLoanType(@RequestBody LoanType loan) {
        return ResponseEntity.ok().body(loanService.addLoan(loan));
    }

    @GetMapping("/admin/getallloantype")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<LoanType>> getAllLoanType() {
        return ResponseEntity.ok().body(loanService.getAllLoan());
    }

    @PutMapping("/admin/updateloan")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<LoanType> updateLoanType(@RequestBody LoanType loanType) {
        LoanType loan = loanService.updateLoan(loanType);
        return ResponseEntity.ok().body(loan);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<String> deleteLoanLoanType(@PathVariable String id) {
        loanService.deleteById(id);
        return ResponseEntity.ok("Successfully delete loan type by id");
    }

    @PostMapping("/one-month")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> addOneMonthLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction transaction = loanTransactionService.addLoanTransaction_oneMonth(request);
            LoanTransactionResponse response = convertToResponse(transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/three-month")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> threeMontLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction transaction = loanTransactionService.addLoanTransaction_threeMonth(request);
            LoanTransactionResponse response = convertToResponse(transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/sixth-month")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> sixthMonthLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction transaction = loanTransactionService.addLoanTransaction_sixthMonth(request);
            LoanTransactionResponse response = convertToResponse(transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/nine-month")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> nineMonthLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction transaction = loanTransactionService.addLoanTransaction_NineMonth(request);
            LoanTransactionResponse response = convertToResponse(transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/twelve-month")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> twelveMonthLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction transaction = loanTransactionService.addLoanTransaction_TwelveMonth(request);
            LoanTransactionResponse response = convertToResponse(transaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/admin/loanTransaction")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<List<LoanTransactionResponse>> getAllLoanTransaction() {
        List<LoanTransaction> transactions = loanTransactionService.getAllLoanTransactions();
        return ResponseEntity.ok().body(transactions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/admin/loanTransaction/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransactionResponse> getLoanTransactionById(@PathVariable String id) {
        LoanTransaction transaction = loanTransactionService.getLoanById(id);
        LoanTransactionResponse transactionResponse = convertToResponse(transaction);
        return ResponseEntity.ok().body(transactionResponse);
    }

    @DeleteMapping("/admin/loanTransaction/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<String> deleteLoanTransaction(@PathVariable String id) {
        loanTransactionService.deleteById(id);
        return ResponseEntity.ok("Successfully delete loan transaction by id");
    }

    @PutMapping("/pay")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<String> updateLoanTransactionBalance(@RequestBody LoanTransactionRequest request) {
        try {
            String paymentUrl = loanTransactionService.processLoanPayment(request);
            return ResponseEntity.ok("Payment URL: " + paymentUrl);
        } catch (RuntimeException e) {
            logger.error("Error processing payment: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PutMapping("/admin/changeloanbalance")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<LoanTransaction> updateLoanBalance(@RequestBody LoanTransactionRequest request) {
        LoanTransaction transaction = loanTransactionService.changeLoanBalance(request);
        return ResponseEntity.ok().body(transaction);
    }

    private LoanTransactionResponse convertToResponse(LoanTransaction transaction) {
        List<LoanTransactionDetailResponse> transactionDetailResponses = transaction.getLoanTransactionDetails().stream()
                .map(this::convertToResponse).toList();

        return LoanTransactionResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .loanTypeId(transaction.getLoanType().getId())
                .instalmentTypeId(transaction.getInstalmentType().getId())
                .nominal(transaction.getNominal())
                .approvedAt(System.currentTimeMillis())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .approvedBy(transaction.getApprovedBy())
                .approvalStatus(transaction.getApprovalStatus().toString())
                .loanTransactionDetails(transactionDetailResponses)
                .build();
    }


    private LoanTransactionDetailResponse convertToResponse(LoanTransactionDetail detail) {
        return LoanTransactionDetailResponse.builder()
                .id(detail.getId())
                .nominal(detail.getNominal())
                .transactionDate(detail.getTransactionDate())
                .loanStatus(detail.getLoanStatus().name())
                .build();
    }
}
