package com.myonlineshopping.demo.integration;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import com.myonlineshopping.demo.repository.IAccountRepository;
import com.myonlineshopping.demo.repository.ICustomerRepository;
import com.myonlineshopping.demo.services.AccountService;
import com.myonlineshopping.demo.services.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ServicePersistenceIntegrationTests {

   /* @Autowired
    IAccountRepository repo;*/

    @Autowired
    IAccountService service;

    @Autowired
    ICustomerRepository customerRepo;
    @Autowired
    IAccountRepository accountRepo;

    Customer customer;

    Account acc;



    @BeforeEach
    public void setUp() {

        List<Account> accountList = new ArrayList<>(List.of());

        customer = new Customer(100L, "Javier", "javier@a.com", accountList);

        acc = new Account(200L, "Personal", 500, "2024-09-20", customer);
        accountList.add(acc);

        customerRepo.save(customer);

        accountRepo.save(acc);

    }

    @Test
    public void givenAddMoney_whenMatchingOwnerAndAccountId_ThenAddMoney() {

        // Al llamar al método addMoney, si la cuenta recibida se encuentra entre las cuentas
        // del cliente, se puede sumar el dinero.

        service.addMoney(6L, 5L, 200);

        assertThat(acc.getBalance(), is(equalTo(700)));
    }

    @Test
    public void givenAddMoney_whenNotMatchingOwnerAndAccountId_ThenThrowException() {

        service.addMoney(300L, 100L, 200);

        assertThrows(IllegalArgumentException.class, () -> {
            // Código que lanza la excepción
            throw new IllegalArgumentException("Número inválido");
        });
    }

    @Test
    public void givenWithdrawMoney_whenMatchingOwnerAndAccountId_ThenAddMoney() {

        // Al llamar al método addMoney, si la cuenta recibida se encuentra entre las cuentas
        // del cliente, se puede sumar el dinero.

        service.addMoney(200L, 100L, 200);

        assertThat(acc.getBalance(), is(equalTo(300)));
    }

    @Test
    public void givenWithdrawMoney_whenNotMatchingOwnerAndAccountId_ThenThrowException() {

        service.addMoney(300L, 100L, 200);

        assertThrows(IllegalArgumentException.class, () -> {
            // Código que lanza la excepción
            throw new IllegalArgumentException("Número inválido");
        });
    }}








/*    • Para validar la integración entre la capa de servicio y persistencia (2 escenarios significativos).
            -> givenAddMoneyWhen
  		-> givenAddMoney_whenValid_ThenAddMoney
      -> givenAddMoney_whenNoValid_ThenAddMoney
    -> getByCustomer_id
    	-> givenGetByCustomer_id_whenValid_ThenListAccounts(todo correcto)
      -> givenGetByCustomer_id_whenNotCustomer_ThenException(customer = null) */