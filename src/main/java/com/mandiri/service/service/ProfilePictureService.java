package com.mandiri.service.service;

import com.mandiri.entity.ProfilePicture;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public interface ProfilePictureService {
    ProfilePicture addProfilePictureToCustomer(String customerId, MultipartFile file) throws IOException;
    void deleteProfilePicture(String id);
}
