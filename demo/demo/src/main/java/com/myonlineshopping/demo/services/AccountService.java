package com.myonlineshopping.demo.services;

import com.myonlineshopping.demo.controller.AccountController;
import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import com.myonlineshopping.demo.repository.IAccountRepository;
import com.myonlineshopping.demo.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    ICustomerRepository iCustomerRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }



    public List<Account> getByCustomer_id(Long customer) throws Exception{

        return accountRepository.findByOwnerId(customer);
    }
    public Optional<Account> getAccount(Long id){
        return accountRepository.findById(id);
    }
    public void saveAccount(Account account,Long ownerId){
        //TODO  PONER EXCEPCIÓN SI NO EXISTE
        Customer cus = iCustomerRepository.findById(ownerId).get();
        account.setOwner(cus);
        accountRepository.save(account);
    }
    public Account updateAccount(Account account,Long accountId){
         //TODO  PONER EXCEPCIÓN SI NO EXISTE
        //TODO A VER SI HACE FALTA CAMBIAR MÁS PARAMETROS DE LA CUENTA
        Account acc= accountRepository.findById(accountId).get();
        acc.setBalance(account.getBalance());
        return accountRepository.save(acc);
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    public void deleteAccount(Account account){
        accountRepository.delete(account);
    }
    public void deleteAccountById(Long id){
        accountRepository.deleteById(id);
    }
    public void deleteByCustomer(Long customer){
        accountRepository.deleteByOwnerId(customer);
    }
    @Transactional
    public Account addMoney(Long cuentaId, Long idCustomer,Integer dinero){
        accountRepository.addMoney(cuentaId,idCustomer,dinero);
        return accountRepository.findById(cuentaId).get();
    }
    @Transactional
    public Account withdrawMoney(Long cuentaId,Long cantidad, Integer customer) throws Exception{
        if((accountRepository.findById(cuentaId).get().getBalance())>=cantidad){
            accountRepository.withdrawMoney(cuentaId,cantidad,customer);
            return accountRepository.findById(cuentaId).get();
        }else
            throw new Exception();
    }
    public Integer totalBalance(Long id){

        return accountRepository.totalBalance(id);
    }


    public boolean checkPrestamo(Integer amount, Integer balance) {

        if(balance*(AccountController.PORCENTAJE_MAXIMO)>=amount){
            return true;
        }else return false;
    }
}
