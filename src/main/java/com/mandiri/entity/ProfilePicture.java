package com.mandiri.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile_picture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    private byte[] data;

    private String fileName;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;


}
