package com.mandiri.service.service;

import com.mandiri.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleService {
    Role getOrSave(Role.ERole role);
}
