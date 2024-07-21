package com.mandiri.controller;

import com.mandiri.dto.reponse.CommonResponse;
import com.mandiri.dto.reponse.LoginResponse;
import com.mandiri.dto.reponse.RegisterResponse;
import com.mandiri.dto.request.AuthRequest;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService auth;

    @PostMapping("/signup/customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody AuthRequest<CustomerRequest> request){
        RegisterResponse response = auth.registerCustomer(request);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully Register new Customer")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);

    }

    @PostMapping("/signup/admin")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerAdmin(@RequestBody AuthRequest<CustomerRequest> request){
        RegisterResponse response = auth.registerAdmin(request);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully Register new Admin")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);

    }

    @PostMapping("/signup/staff")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerStaff(@RequestBody AuthRequest<CustomerRequest> request){
        RegisterResponse response = auth.registerStaff(request);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully Register new Staff")
                .data(Optional.of(response))
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);

    }

    @PostMapping("/signin")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest<String> request){
        LoginResponse response = auth.login(request);
        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Login success")
                .data(Optional.of(response))
                .build();
        return ResponseEntity.ok(commonResponse);

    }
}
