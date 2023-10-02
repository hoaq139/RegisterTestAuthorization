package com.example.registertestauthorization.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountDTOResponse {
    private String id;
    private String username;
    private String token;
    private Date dob;
    private Date registerDate;
    private int status;
    private long roleId;
    private String address;
    private String fullName;
    private String gender;
    private String identityCard;
    private String image;
    private String phoneNumber;
    private String email;
}
