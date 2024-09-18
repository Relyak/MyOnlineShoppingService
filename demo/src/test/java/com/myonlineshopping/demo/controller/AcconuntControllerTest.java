package com.myonlineshopping.demo.controller;

import com.myonlineshopping.demo.dto.AccountDTO;
import com.myonlineshopping.demo.dto.Balance;
import com.myonlineshopping.demo.exceptions.AccountNotfoundException;
import com.myonlineshopping.demo.model.Account;
import com.myonlineshopping.demo.services.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AcconuntControllerTest {

    @Autowired
    AccountController accountController;
    @Autowired
    IAccountService iAccountService;

    @Test
    public void givenAddBalanceWhenAddBalanceValidThenAccepted() {
        // Crear el objeto Balance de prueba
        Balance balance = new Balance();
        balance.setIdCuenta(1L);
        balance.setIdPropietario(1L);
        balance.setDinero(500);

        ResponseEntity<AccountDTO> response = accountController.addBalance(balance);


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
    public void givenAccountWhenDeleteByCustomerThenIdIsNotAllowed() throws Exception {
        // ID no permitido
        Long notAllowedId = 9L;

        // Llamada al controlador para eliminar la cuenta con el ID no permitido
        ResponseEntity<Account> response = accountController.deleteCuenta(notAllowedId);

        // Verificar que el cuerpo de la respuesta sea nulo, ya que no se encontró una cuenta para este ID
        assertThat(response.getBody(), is(nullValue()));
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
    @Test
    public void givenValidOwnerIdAndAmount_whenCheckPrestamo_thenValid() {
        // Parámetros de prueba
        Long ownerId = 1L; // ID de usuario válido
        Integer cantidad = 500; // Cantidad de préstamo solicitada

        // Llamada al endpoint
        ResponseEntity<String> response = accountController.checkPrestamo(ownerId, cantidad);

        // Verificar el estado de la respuesta
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        // Verificar que el cuerpo de la respuesta sea "Es valido"
        assertThat(response.getBody(), is("Es valido"));
    }

    @Test
    public void givenValidOwnerIdAndAmount_whenCheckPrestamo_thenHigherAllowed() {
        int ownerId = 1;
        Integer cantidad = 277000;
        Boolean response = iAccountService.checkPrestamo(cantidad, ownerId);

        // Verificar que el préstamo no es permitido (por ser una cantidad alta)
        assertThat(response, is(false));
    }

}




