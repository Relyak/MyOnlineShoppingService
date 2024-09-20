package com.myonlineshopping.controller;

import com.myonlineshopping.model.Account;
import com.myonlineshopping.model.ERole;
import com.myonlineshopping.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:data.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerTestRestTemplate extends AccountControllerAbstractTest{
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    User user;
    String token;
//abstract
    @BeforeAll
    public void setUp(){
        user = createUser("MANOLITO@MANOLITO.COM","12345", ERole.CAJERO);
        persistUser(user);


    }
    @BeforeEach
    public void getToken(){
        token = setUp(user);
    }

    @Test
    public void givenGetCuentasDeUnUsuarioWhenExistsThenExist() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Account[]> response = restTemplate.getForEntity("http://localhost:" + port + "/account/owner/1", Account[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
    }
    @Test
    public void givenGetCuentasDeUnUsuarioWhenNoExistsThenNoExist() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        assertThrows(Exception.class, () -> {
            ResponseEntity<Account[]> response = restTemplate.getForEntity("http://localhost:" + port + "/account/owner/131", Account[].class);
        });
    }
    @Test
    public void givenCheckPrestamoWhenOkThenOk() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:" + port + "/account/owner/1/prestamo/100",String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(),is(containsString("Es valido")));
        assertThat(response.getBody(), is(notNullValue()));
    }
    @Test
    public void givenCheckPrestamoWhenNoValidoThenOk() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:" + port + "/account/owner/1/prestamo/-103200",String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), is(notNullValue()));
    }




}
