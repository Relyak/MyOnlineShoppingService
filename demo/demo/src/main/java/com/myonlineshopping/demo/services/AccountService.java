package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import com.myonlineshopping.demo.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public List<Account> getByCustomer_id(Customer customer){

        return accountRepository.findByOwnerId(customer);
    }
    public Optional<Account> getAccount(Long id){
        return accountRepository.findById(id);
    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }
    public void updateAccount(Account account){
        accountRepository.save(account);
    }
    public void deleteAccount(Account account){
        accountRepository.delete(account);
    }
    public void deleteAccountById(Long id){
        accountRepository.deleteById(id);
    }
    public void deleteByCustomer(Customer customer){
        accountRepository.deleteByOwner(customer);
    }
    public void addMoney(Long cuentaId,int cantidad, Customer customer){
        accountRepository.addMoney(cuentaId,cantidad,customer);
    }
    public void withdrawMoney(Long cuentaId,int cantidad, Customer customer) throws Exception{
        if((accountRepository.findById(cuentaId).get().getBalance())>=cantidad){
            accountRepository.withdrawMoney(cuentaId,cantidad,customer);
        }else
            throw new Exception();
    }

}
