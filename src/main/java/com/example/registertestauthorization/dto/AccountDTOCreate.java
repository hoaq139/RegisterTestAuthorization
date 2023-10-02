package com.example.registertestauthorization.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@Builder
public class AccountDTOCreate {
    String id;
    String username;
    String password;
    Date dob;
    Date registerDate;
    int status;
    long roleId;
    String address;
    String fullName;
    String gender;
    String identityCard;
    String image;
    String phoneNumber;
    String email;
}
