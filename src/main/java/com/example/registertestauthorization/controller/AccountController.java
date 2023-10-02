package com.example.registertestauthorization.controller;

import com.example.registertestauthorization.dto.AccountDTOCreate;
import com.example.registertestauthorization.dto.AccountDTOResponse;
import com.example.registertestauthorization.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/users")
    public Map<String, AccountDTOResponse> registerUser(
            @RequestBody Map<String, AccountDTOCreate> userDTOCreateMap) {
        return accountService.registerUser(userDTOCreateMap);
    }
}
