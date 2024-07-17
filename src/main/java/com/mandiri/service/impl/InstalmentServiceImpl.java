package com.mandiri.service.impl;

import com.mandiri.entity.InstalmentType;
import com.mandiri.entity.Role;
import com.mandiri.repository.InstallmentRepository;
import com.mandiri.service.service.InstalmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstalmentServiceImpl implements InstalmentService {
    private final InstallmentRepository installmentRepository;

    public InstalmentType getOrSave(InstalmentType.EInstalmentType instalmentType){
        Optional<InstalmentType> optionalInstalmentType = installmentRepository.findByInstalmentType(instalmentType);
        // role available return it
        if (optionalInstalmentType.isPresent()) {
            return optionalInstalmentType.get();
        }

        // role not available create new
        InstalmentType instalmentType1 = InstalmentType.builder()
                .instalmentType(instalmentType)
                .build();

        return installmentRepository.saveAndFlush(instalmentType1);
    }
}
