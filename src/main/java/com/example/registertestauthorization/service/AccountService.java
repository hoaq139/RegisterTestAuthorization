package com.example.registertestauthorization.service;

import com.example.registertestauthorization.dto.AccountDTOCreate;
import com.example.registertestauthorization.dto.AccountDTOResponse;

import java.util.Map;

public interface AccountService {
    Map<String, AccountDTOResponse> registerUser(Map<String, AccountDTOCreate> userDTOCreateMap);
}
