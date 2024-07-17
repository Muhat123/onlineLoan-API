package com.mandiri.service.impl;

import com.mandiri.entity.AppUser;
import com.mandiri.entity.User;
import com.mandiri.repository.UserRepository;
import com.mandiri.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credential User")
        );

        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRoles().get(0).getName())
                .build();
    }

    @Override
    public AppUser loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credential User")
        );

        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRoles().get(0).getName())
                .build();

    }

}
