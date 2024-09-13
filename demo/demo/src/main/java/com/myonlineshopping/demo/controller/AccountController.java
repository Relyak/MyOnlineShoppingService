package com.myonlineshopping.demo.controller;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Ingresos;
import com.myonlineshopping.demo.services.AccountService;
import com.myonlineshopping.demo.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    IAccountService iAccountService;

    public String account() {
        return "account";

    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Account>> getCuentasDeUnUsuario(@PathVariable Long ownerId){
        return ResponseEntity.ok(iAccountService.getByCustomer_id(ownerId));
    }
    @PostMapping("/owner/{ownerId}")
    public ResponseEntity<Account> postCuenta(@RequestBody Account account,@PathVariable Long ownerId){
        iAccountService.saveAccount(account,ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> deleteCuenta(@PathVariable("accountId") Long accountId){
        System.out.println("Borrando cuenta::::");
        iAccountService.deleteAccountById(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> putCuenta(@RequestBody Account account,@PathVariable Long accountId){
        Account acc = iAccountService.updateAccount(account,accountId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(acc);
    }
    @PutMapping
    public ResponseEntity<Account> addBalance(@RequestBody Ingresos ingresos){
        iAccountService.addMoney(ingresos.getIdCuenta(),ingresos.getIdPropietario(),ingresos.getDinero());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}