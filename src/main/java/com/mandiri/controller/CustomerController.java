package com.mandiri.controller;

import com.mandiri.dto.reponse.AvatarResponse;
import com.mandiri.dto.reponse.CommonResponse;
import com.mandiri.dto.reponse.CustomerResponse;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.entity.ProfilePicture;
import com.mandiri.service.service.CustomerService;
import com.mandiri.service.service.LoanTransactionService;
import com.mandiri.service.service.ProfilePictureService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Authorization")
public class CustomerController {
    private final CustomerService customerService;
    private final LoanTransactionService loanTransactionService;
    private final ProfilePictureService profilePictureService;

    @PostMapping("/admin/postCustomer")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<CustomerResponse> postCustomer(@RequestBody CustomerRequest request) {
        log.info("Received request in controller: {}", request);
        CustomerResponse customerResponse = customerService.saveCustomer(request);
        return ResponseEntity.ok(customerResponse);
    }


    @GetMapping("/admin/allcustomer")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity.ok().body(customerService.getAllCustomer());
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomerById(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok().body(customerService.detailCustomer(id));
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Successfully delete by id");
    }

    @PostMapping("/avatar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<CommonResponse<AvatarResponse>> uploadAvatar(
            @RequestParam("avatar") MultipartFile avatar,
            HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("Received userId: " + userId);


        try {
            ProfilePicture profilePicture = profilePictureService.addProfilePictureToCustomer(userId, avatar);
            String name = profilePicture.getFileName();
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/customer")
                    .path("/avatar")
                    .path(name)
                    .toUriString();

            AvatarResponse avatarResponse = AvatarResponse.builder()
                    .url(fileDownloadUri)
                    .name(name)
                    .build();

            CommonResponse<AvatarResponse> commonResponse = CommonResponse.<AvatarResponse>builder()
                    .message("File uploaded successfully")
                    .statusCode(HttpStatus.CREATED.value())
                    .data(Optional.of(avatarResponse))
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    CommonResponse.<AvatarResponse>builder()
                            .message("Failed to upload file")
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build()
            );
        }
    }

    @DeleteMapping("/avatar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_STAFF')")
    public ResponseEntity<String> deleteAvatar(@PathVariable String id) {
        profilePictureService.deleteProfilePicture(id);
        return ResponseEntity.ok("Successfully delete avatar by id");
    }

}
