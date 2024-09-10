package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public List<Account> getAllAccounts();

    public List<Account> getByCustomer_id(Long customer);

    public Optional<Account> getAccount(Long id);

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(Account account);

    public void deleteAccountById(Long id);

    public void deleteByCustomer(Customer customer);

    public void addMoney(Long cuentaId, int cantidad, Customer customer);

    public void withdrawMoney(Long cuentaId, int cantidad, Customer customer) throws Exception;
}
