package com.example.registertestauthorization.mapper;

import com.example.registertestauthorization.dto.AccountDTOResponse;
import com.example.registertestauthorization.entity.Account;
import com.example.registertestauthorization.dto.AccountDTOCreate;
import com.example.registertestauthorization.entity.Roles;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTORegister){
        return Account.builder()
                .accountId(accountDTORegister.getId())
                .userName(accountDTORegister.getUsername())
                .password(accountDTORegister.getPassword())
                .fullName(accountDTORegister.getFullName())
                .address(accountDTORegister.getAddress())
                .image(accountDTORegister.getImage())
                .phone(accountDTORegister.getPhoneNumber())
                .roles(Roles.builder()
                        .roleId(accountDTORegister.getRoleId())
                        .build())
                .gender(accountDTORegister.getGender())
                .status(accountDTORegister.getStatus())
                .registerDate(accountDTORegister.getRegisterDate())
                .identityCard(accountDTORegister.getIdentityCard())
                .dob(accountDTORegister.getDob())
                .email(accountDTORegister.getEmail())
                .build();
    }
    public static AccountDTOResponse toAccountResponse(Account account){
        return AccountDTOResponse.builder()
                .id(account.getAccountId())
                .username(account.getUserName())
                .fullName(account.getFullName())
                .address(account.getAddress())
                .image(account.getImage())
                .phoneNumber(account.getImage())
                .roleId(account.getRoles().getRoleId())
                .gender(account.getGender())
                .status(account.getStatus())
                .registerDate(account.getRegisterDate())
                .identityCard(account.getIdentityCard())
                .dob(account.getDob())
                .email(account.getEmail())
                .build();
    }
}
