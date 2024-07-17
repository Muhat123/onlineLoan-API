package com.mandiri.dto.request;

import com.mandiri.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private User user;
}
