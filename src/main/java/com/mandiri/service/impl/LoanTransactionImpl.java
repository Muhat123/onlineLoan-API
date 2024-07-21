package com.mandiri.service.impl;

import com.mandiri.dto.request.LoanTransactionRequest;
import com.mandiri.entity.*;
import com.mandiri.repository.LoanTransactionDetailRepository;
import com.mandiri.repository.LoanTransactionRepository;
import com.mandiri.service.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
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
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(LoanTransactionService.class);


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

    public LoanTransaction addLoanTransaction_threeMonth(LoanTransactionRequest request) {
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.THREE_MONTHS);

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

    public LoanTransaction addLoanTransaction_sixthMonth(LoanTransactionRequest request) {
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.SIXTH_MONTHS);

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

    public LoanTransaction addLoanTransaction_NineMonth(LoanTransactionRequest request) {
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.NINE_MONTHS);

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

    public LoanTransaction addLoanTransaction_TwelveMonth(LoanTransactionRequest request) {
        Customer customer = service.getById(request.getCustomerId());
        LoanType loanType = loanService.getLoanTypeById(request.getLoanTypeId());
        InstalmentType instalmentType = instalmentService.getOrSave(InstalmentType.EInstalmentType.TWELVE_MONTHS);

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


    public List<LoanTransaction> getAllLoanTransactions() {
        return loanTransactionRepository.findAll();
    }

    public LoanTransaction getLoanById(String id) {
        return findByIdOrThrowNotFound(id);

    }

    public void deleteById(String id){
         loanTransactionRepository.deleteById(id);
    }

//    public LoanTransaction updateLoan(LoanTransaction transaction){
//        LoanTransaction loan = findByIdOrThrowNotFound(transaction.getId());
////        loan.setLoanTransactionDetails(transaction.getLoanTransactionDetails().stream().map(LoanTransactionDetail::setLoanStatus));
//    }


    private LoanTransaction findByIdOrThrowNotFound(String id){
        return loanTransactionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }


    public LoanTransaction updateBalanceLoan(LoanTransactionRequest request){
        LoanTransaction loan = findByIdOrThrowNotFound(request.getId());

        if (loan.getNominal() - request.getNominal() >= 0) {
            loan.setNominal(loan.getNominal() - request.getNominal());
        } else {
            throw new RuntimeException("you pay more than the remaining installment amount");
        }
        loan.setUpdatedAt(System.currentTimeMillis());
        return loanTransactionRepository.save(loan);
    }

    public String processLoanPayment(LoanTransactionRequest request) {
        LoanTransaction updatedLoan = updateBalanceLoan(request);
        return createTransaction(request.getId(), request.getNominal(), updatedLoan.getLoanTransactionDetails());
    }

    public LoanTransaction changeLoanBalance(LoanTransactionRequest request){
        LoanTransaction loan = findByIdOrThrowNotFound(request.getId());
        loan.setNominal(request.getNominal());
        loan.setUpdatedAt(System.currentTimeMillis());
        return loanTransactionRepository.save(loan);
    }
    public String createTransaction(String id, Double totalPayment, List<LoanTransactionDetail> details) {
        String uniqueOrderId = id + "-" + System.currentTimeMillis();
        String url = "https://app.sandbox.midtrans.com/snap/v1/transactions";
        String midtransServerKey = "SB-Mid-server-2pi_hGKts-eAXfe5lnZ1ObUO";

        // Prepare request payload
        Map<String, Object> params = new HashMap<>();

        //
        Map<String, Object> transactionDetails = new HashMap<>();
        params.put("transaction_details", transactionDetails);
        transactionDetails.put("order_id", uniqueOrderId);
        transactionDetails.put("gross_amount", totalPayment);

        //
        List<Map<String, Object>> itemDetails = new ArrayList<>();
        for (LoanTransactionDetail detail : details) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", detail.getId());
            item.put("price", totalPayment);
            item.put("quantity", 1);
            item.put("name", "Pembayaran cicilan");
            itemDetails.add(item);
        }
        params.put("item_details", itemDetails);

        //
        Map<String, Object> creditCard = new HashMap<>();
        params.put("credit_card", creditCard);
        creditCard.put("secure", true);

        // Send request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((midtransServerKey + ":").getBytes()));


            ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                Map.class
        );

        Map<String, Object> responseBody = response.getBody();
        logger.debug("Received response from Midtrans: {}", responseBody);

        if (responseBody != null && responseBody.containsKey("redirect_url")) {
            return (String) responseBody.get("redirect_url");
        } else {
            throw new RuntimeException("Failed to create transaction: " + responseBody);
        }
    }
}

