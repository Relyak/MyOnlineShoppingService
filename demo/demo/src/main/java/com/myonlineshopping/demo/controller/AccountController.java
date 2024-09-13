package com.myonlineshopping.demo.controller;

import com.myonlineshopping.demo.dto.AccountDTO;
import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.dto.Balance;
import com.myonlineshopping.demo.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

public class AccountController {

    @Autowired
    IAccountService iAccountService;

    public String account() {
        return "account";

    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<AccountDTO>> getCuentasDeUnUsuario(@Min(1)@PathVariable Long ownerId) {
        List<Account> account = iAccountService.getByCustomer_id(ownerId);
        List<AccountDTO> accountDTOs = account.stream().map(a -> AccountDTO.createAccountDto(a)).collect(Collectors.toList());

        return ResponseEntity.ok(accountDTOs);
    }

    @PostMapping(value = "/owner/{ownerId}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountDTO> postCuenta(@Valid @RequestBody Account account, @Min(1) @PathVariable Long ownerId) {
        iAccountService.saveAccount(account, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountDTO.createAccountDto(account));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> deleteCuenta(@Min(1)@PathVariable("accountId") Long accountId) {
        System.out.println("Borrando cuenta::::");
        iAccountService.deleteAccountById(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Transactional
    @DeleteMapping("/owner/{ownerId}")
    public ResponseEntity<Account> deleteAllCuentas(@Min(1)@PathVariable("ownerId") Long ownerId) {
        System.out.println("Borrando cuenta::::");
        iAccountService.deleteByCustomer(ownerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value="/{accountId}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountDTO> putCuenta(@Valid @RequestBody Account account,@Min(1) @PathVariable Long accountId) {
        Account acc = iAccountService.updateAccount(account, accountId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AccountDTO.createAccountDto(acc));
    }

    @PutMapping(value="/add",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountDTO> addBalance(@Valid @RequestBody Balance balance) {
        Account acc = iAccountService.addMoney(balance.getIdCuenta(), balance.getIdPropietario(), balance.getDinero());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AccountDTO.createAccountDto(acc));
    }

    @PutMapping(value="/withdraw",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountDTO> withDrawBalance(@Valid @RequestBody Balance balance) throws Exception {
        Account acc = iAccountService.withdrawMoney(balance.getIdCuenta(), balance.getIdPropietario(), balance.getDinero());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AccountDTO.createAccountDto(acc));
    }
    @GetMapping(value="/owner/{ownerId}/prestamo/{cantidad}")
    public ResponseEntity<String> checkPrestamo(@PathVariable Long ownerId,@PathVariable Integer cantidad){
        Integer balanceTotal = iAccountService.totalBalance(ownerId);
        boolean prestamo = iAccountService.checkPrestamo(cantidad,balanceTotal);
        if(prestamo){
            return ResponseEntity.ok("Es valido");
        }else{
            return ResponseEntity.ok("No valido");
        }

    }

}