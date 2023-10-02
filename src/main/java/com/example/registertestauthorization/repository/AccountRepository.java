package com.example.registertestauthorization.repository;

import com.example.registertestauthorization.controller.AccountController;
import com.example.registertestauthorization.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
}
