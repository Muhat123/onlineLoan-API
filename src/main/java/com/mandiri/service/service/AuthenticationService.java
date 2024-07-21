package com.mandiri.service.service;

import com.mandiri.dto.reponse.LoginResponse;
import com.mandiri.dto.reponse.RegisterResponse;
import com.mandiri.dto.request.AuthRequest;
import com.mandiri.dto.request.CustomerRequest;

public interface AuthenticationService {
    RegisterResponse registerCustomer(AuthRequest<CustomerRequest> authRequest);
    LoginResponse login(AuthRequest<String> request);
    RegisterResponse registerAdmin(AuthRequest<CustomerRequest> request);
    RegisterResponse registerStaff(AuthRequest<CustomerRequest> request);
}
