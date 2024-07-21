package com.mandiri.service.impl;

import com.mandiri.Security.JwtUtil;
import com.mandiri.Security.exception.ResourceNotFoundException;
import com.mandiri.dto.reponse.LoginResponse;
import com.mandiri.dto.reponse.RegisterResponse;
import com.mandiri.dto.request.AuthRequest;
import com.mandiri.dto.request.CustomerRequest;
import com.mandiri.entity.AppUser;
import com.mandiri.entity.Customer;
import com.mandiri.entity.Role;
import com.mandiri.entity.User;
import com.mandiri.repository.UserRepository;
import com.mandiri.service.service.AuthenticationService;
import com.mandiri.service.service.CustomerService;
import com.mandiri.service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthenticaionServiceImpl implements AuthenticationService {

    private final RoleService roleService;
    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final AuthenticationManager auth;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    @Transactional
    public RegisterResponse registerCustomer(AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        CustomerRequest requestData = request.getData().orElseThrow(
                () -> new ResourceNotFoundException("Customer not found")
        );


        User user = User.builder()
                .username(request.getUsername().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .dateOfBirth(requestData.getDateOfBirth())
                .build();


        user = userRepository.saveAndFlush(user);


        requestData.setUser(user);// set relation customer to user

        customerService.saveCustomer(requestData);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(role.getName())
                .build();
    }

    public RegisterResponse registerAdmin(AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_ADMIN);
        List<Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(role);

        User user = User.builder()
                .username(request.getUsername().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleAdmin)
                .build();

        user = userRepository.saveAndFlush(user);

        CustomerRequest requestData = request.getData().orElseThrow(
                () -> new ResourceNotFoundException("Admin not found")
        );

        requestData.setUser(user);  // set relation customer to user

        customerService.saveCustomer(requestData);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(role.getName())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest<String> request) {

        Authentication authentication = auth.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();

        // TODO: Generate Token!
        //TODO: Create Algorithm

        String token = jwtUtil.generateToken(appUser);


        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole())
                .build();

    }

    public RegisterResponse registerStaff(AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_STAFF);
        List<Role> roleStaff = new ArrayList<>();
        roleStaff.add(role);

        User user = User.builder()
                .username(request.getUsername().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleStaff)
                .build();

        user = userRepository.saveAndFlush(user);

        CustomerRequest requestData = request.getData().orElseThrow(
                () -> new ResourceNotFoundException("Staff not found")
        );

        requestData.setUser(user);

        customerService.saveCustomer(requestData);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(role.getName())
                .build();
    }
}
