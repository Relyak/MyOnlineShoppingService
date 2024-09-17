package com.myonlineshopping.demo.controller;

import com.myonlineshopping.demo.dto.AccountDTO;
import com.myonlineshopping.demo.dto.Balance;
import com.myonlineshopping.demo.exceptions.AccountNotfoundException;
import com.myonlineshopping.demo.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AcconuntControllerTest {

    @Autowired
    AccountController accountController;

    @Test
    public void givenAddBalanceWhenAddBalanceValidThenAccepted() {
        // Crear el objeto Balance de prueba
        Balance balance = new Balance();
        balance.setIdCuenta(1L);
        balance.setIdPropietario(1L);
        balance.setDinero(500);

        ResponseEntity<AccountDTO> response = accountController.addBalance(balance);

        // Verificar que el status sea 202 ACCEPTED
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.ACCEPTED.value());

        // Verificar que los datos del objeto AccountDTO son correctos
        AccountDTO accountDTO = response.getBody();
        assertThat(accountDTO, is(notNullValue()));
        assertThat(accountDTO.getId(), is(1L));
        assertThat(accountDTO.getOwnerId(), is(1L));
        assertThat(accountDTO.getBalance(), is(1500));  // Saldo actualizado
    }
//faltaria implementar en el service, que no permita trabajar con ingresos en negativos
    @Test
    public void givenAddBalanceWhenAddBalanceNegativeThenPreconditionFailed() {

        Balance balance = new Balance();
        balance.setIdCuenta(1L);
        balance.setIdPropietario(1L);
        balance.setDinero(-1500);  // Saldo negativo (no permitido)

     ResponseEntity<AccountDTO> response = accountController.addBalance(balance);

    assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.PRECONDITION_FAILED.value());
    }

    @Test
    public void givenAccountWhenGetByCustomer_idThenValid() throws Exception {
        Long ownerId = 3L;

        // Invocar el método del controlador directamente
        ResponseEntity<List<AccountDTO>> response = accountController.getCuentasDeUnUsuario(ownerId);

        // Verificar que el estado sea 200 OK
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        // Verificar que la lista de cuentas no sea nula y tenga dos elementos
        List<AccountDTO> accountDTOs = response.getBody();
        assertThat(accountDTOs, is(notNullValue()));

        // Verificar los valores de las cuentas
        assertThat(accountDTOs.get(0).getId(), is(3L));
        assertThat(accountDTOs.get(0).getBalance(), is(80000));
    }

    @Test
    public void givenAccountWhenGetByCustomer_idThenNegative() throws Exception {
        Long ownerId = -1L;

        // Invocar el método del controlador con ownerId negativo
        try {
            accountController.getCuentasDeUnUsuario(ownerId);
        } catch (Exception e) {
            // Verificar que se lanzó la excepción con el mensaje correcto
            assertThat(e.getMessage(), containsString("Owner ID no puede ser negativo"));
        }
    }

    @Test
    public void givenAccountWhenDeleteByCustomerThenIsOK (){
        ResponseEntity<Account> response = accountController.deleteCuenta(1L);
        assertThat(response.getStatusCode().value()).isEqualTo(204);
    }

    @Test
    public void givenAccountWhenDeleteByCustomerThenIsInvalit () {}




}


