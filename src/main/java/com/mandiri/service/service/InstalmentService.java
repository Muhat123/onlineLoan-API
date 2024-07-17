package com.mandiri.service.service;

import com.mandiri.entity.InstalmentType;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalmentService {
    InstalmentType getOrSave(InstalmentType.EInstalmentType instalmentType);
}
