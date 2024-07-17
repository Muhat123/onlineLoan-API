package com.mandiri.service.service;

import com.mandiri.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);
}
