package com.myonlineshopping.demo.repository;

import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@Sql(value = "classpath:data.sql")
class IAccountRepositoryTest {

    @Autowired
    private IAccountRepository iAccountRepository;

    @Test
    void givenFindByOwnerIdWhenEsValidaThenFind() {
        List<Account> ac = iAccountRepository.findByOwnerId(1L);
        assertThat(ac).isNotNull();
        assertThat(ac.get(0).getOwner().getId()).isEqualTo(1L);
    }

    @Test
    void givenFindByOwnerIdWhenNoEsValidaThenEmpty() {
        assertThat(iAccountRepository.findByOwnerId(999L)).isEmpty();
    }

    @Test
    void givenSaveWhenAccountIsValidThenSave() {
        Account ac = new Account(null, "Corriente", 1230, "2000-12-10", new Customer(1L));
        assertThat(iAccountRepository.save(ac)).isNotNull();
    }

    @Test
    void givenSaveWhenAccountNotValidThenException() {
        assertThrows(Exception.class, () -> {
            iAccountRepository.save(new Account(null, "Corriente", 1230, "3131a131a", new Customer(1L)));
        });
    }

    @Test
    void givenAddMoneyWhenAccountEsValidaThenSave() {
        iAccountRepository.addMoney(1L, 1L, 200);
        assertThat(1200).isEqualTo(iAccountRepository.findById(1L).get().getBalance());
    }

    @Test
    void givenAccountWhenIdIsNotNullThenFind() {
        assertThat(iAccountRepository.findById(1L).get()).isNotNull();
    }

    @Test
    void givenAccountWhenIdIsNullThenException() {
        Long id = null;
        assertThrows(Exception.class, () -> {
            iAccountRepository.findById(id);
        });
    }

    @Test
    void givenWithdrawMoneyWhenIsValidThenSave() {
        iAccountRepository.withdrawMoney(1L, 1L, 200);
        assertThat(800).isEqualTo(iAccountRepository.findById(1L).get().getBalance());
    }

    @Test
    void givenWithdrawMoneyWhenIsNotValidThenException() {
        assertThrows(Exception.class, () -> {
            Optional<Account> accParaAdd = iAccountRepository.findById(null);
            int dinero = 30;
            int total = accParaAdd.get().getBalance() - dinero;
            accParaAdd.get().setBalance(total);
            iAccountRepository.save(accParaAdd.get());
        });
    }

    @Test
    void givenTotalBalanceWhenEsValidoIdCuentaThenNotNull() {
        int totalB = iAccountRepository.totalBalance(1L);
        assertThat(totalB).isNotNull();
        assertThat(totalB).isGreaterThan(0);
    }

    @Test
    void givenTotalBalanceWhenNoEsValidoIdCuentaThenNull() {
        assertThat(iAccountRepository.totalBalance(320L)).isNull();
    }
}