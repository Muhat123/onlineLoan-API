package com.mandiri.repository;

import com.mandiri.entity.InstalmentType;
import com.mandiri.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstallmentRepository extends JpaRepository<InstalmentType, String> {
    Optional<InstalmentType> findByInstalmentType(InstalmentType.EInstalmentType instalmentType);


}
