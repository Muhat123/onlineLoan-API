package com.mandiri.service.impl;

import com.mandiri.Security.exception.ResourceNotFoundException;
import com.mandiri.entity.Customer;
import com.mandiri.entity.ProfilePicture;
import com.mandiri.repository.CustomerRepository;
import com.mandiri.repository.ProfilePictureRepository;
import com.mandiri.repository.UserRepository;
import com.mandiri.service.service.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfilePictureServiceImpl implements ProfilePictureService {

    private final ProfilePictureRepository profilePictureRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;



    @Transactional
    @Override
    public ProfilePicture addProfilePictureToCustomer(String customerId, MultipartFile file) throws IOException {
        Customer customer = customerRepository.findByUserId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        String idFileName = customerId + "_" + fileName;
        byte[] fileData = file.getBytes();

        ProfilePicture profilePicture = ProfilePicture.builder()
                .fileName(idFileName)
                .data(fileData)
                .customer(customer)
                .build();

        profilePicture = profilePictureRepository.save(profilePicture);

        customer.setProfilePicture(profilePicture);
        customerRepository.save(customer);

        return profilePicture;
    }

    public void deleteProfilePicture(String id){
        ProfilePicture profilePicture = profilePictureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile picture not found"));
        customerRepository.findById(profilePicture.getCustomer().getId()).ifPresent(customer -> {
            customer.setProfilePicture(null);
            customerRepository.save(customer);
        });
        profilePictureRepository.deleteById(id);
    }


//    public String storeFile(MultipartFile file, String id) {
//
//        String fileName = Objects.requireNonNull(file.getOriginalFilename());
//        String idFileName = id + "_" + fileName;
//
//        try {
//            Path targetLocation = fileStorageLocation.resolve(idFileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            return idFileName;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
