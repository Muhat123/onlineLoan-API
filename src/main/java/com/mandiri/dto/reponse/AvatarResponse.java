package com.mandiri.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AvatarResponse {
    private String name;
    private String url;
}