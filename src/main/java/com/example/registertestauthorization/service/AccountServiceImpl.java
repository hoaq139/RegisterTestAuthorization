package com.example.registertestauthorization.service;

import com.example.registertestauthorization.dto.AccountDTOCreate;
import com.example.registertestauthorization.dto.AccountDTOResponse;
import com.example.registertestauthorization.entity.Account;
import com.example.registertestauthorization.mapper.AccountMapper;
import com.example.registertestauthorization.repository.AccountRepository;
import com.example.registertestauthorization.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    @Override
    public Map<String, AccountDTOResponse> registerUser(Map<String, AccountDTOCreate> userDTOCreateMap) {
        AccountDTOCreate accountDTOCreate = userDTOCreateMap.get("account");
        Account account = AccountMapper.toAccount(accountDTOCreate);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return buildDTOResponse(account);
    }
    private Map<String, AccountDTOResponse> buildDTOResponse(Account account){
        Map<String, AccountDTOResponse> wrapper = new HashMap<>();
        AccountDTOResponse accountDTOResponse = AccountMapper.toAccountResponse(account);
        accountDTOResponse.setToken(jwtTokenUtil.generateToken(account, 24 * 60 * 60));
        wrapper.put("account", accountDTOResponse);
        return wrapper;
    }
}
