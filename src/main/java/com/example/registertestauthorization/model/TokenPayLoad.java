package com.example.registertestauthorization.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayLoad {
    private String userId;
    private String email;
}
