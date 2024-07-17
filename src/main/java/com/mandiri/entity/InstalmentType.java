package com.mandiri.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instalment_type")
@Builder
public class InstalmentType {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    private EInstalmentType instalmentType;
   public enum EInstalmentType {
        ONE_MONTH,
        THREE_MONTHS,
        SIXTH_MONTHS,
        NINE_MONTHS,
        TWELVE_MONTHS
    }

}


