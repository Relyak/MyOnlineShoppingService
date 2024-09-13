package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public List<Account> getAllAccounts();

    public List<Account> getByCustomer_id(Long customer);

    public Optional<Account> getAccount(Long id);

    public void saveAccount(Account account,Long ownerId);

    public Account updateAccount(Account account,Long ownerId);

    public void deleteAccount(Long accountId);

    public void deleteAccountById(Long ownerId);

    public void deleteByCustomer(Customer customer);

    public void addMoney(Long cuentaId,  Long customerId,Integer cantidad);

    public void withdrawMoney(Long cuentaId, int cantidad, Customer customer) throws Exception;
}
