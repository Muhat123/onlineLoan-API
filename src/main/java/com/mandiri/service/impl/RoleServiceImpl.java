package com.mandiri.service.impl;

import com.mandiri.entity.Role;
import com.mandiri.repository.RoleRepository;
import com.mandiri.service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    public Role getOrSave(Role.ERole role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);
        // role available return it
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        // role not available create new
        Role currentRole = Role.builder()
                .name(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}
