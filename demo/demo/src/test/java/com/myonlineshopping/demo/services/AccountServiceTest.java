package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class AccountServiceTest {
    @Autowired
    IAccountService iAccountService;

    @Test
    void getAllAccounts() {
        List <Account> cuentas = iAccountService.getAllAccounts();
        Assertions.assertNotNull(cuentas);
    }

    @Test
    void getByCustomer_id() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void saveAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void deleteAccountById() {
    }

    @Test
    void deleteByCustomer() {
    }

    @Test
    void addMoney() {
    }

    @Test
    void withdrawMoney() {
    }
}