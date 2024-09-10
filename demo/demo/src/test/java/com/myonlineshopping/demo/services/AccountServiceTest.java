package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    IAccountService iAccountService;

    @Test
    void getAllAccounts() {
        List<Account> cuentas = iAccountService.getAllAccounts();
        Assertions.assertNotNull(cuentas);
    }

    @Test
    void getByCustomer_id() {
        List <Account> cuentas = iAccountService.getByCustomer_id(1L);
        Assertions.assertNotNull(cuentas);
    }

    @Test
    void getAccount() {
        Account cuenta = iAccountService.getAccount(1L).get();
        Assertions.assertNotNull(cuenta);
    }

    @Test
    @Transactional
    void saveAccount() {
        /* En preparación
        Account cuenta = new Account(null,"Personal",3500,"2024-10-09",new Customer(2L));

        iAccountService.saveAccount(cuenta);

        Assertions.assertEquals(iAccountService.getAccount(6L).get().getBalance(),3500);*/

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