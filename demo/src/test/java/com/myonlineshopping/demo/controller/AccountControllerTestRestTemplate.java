package com.myonlineshopping.demo.controller;

import com.myonlineshopping.demo.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:data.sql")

public class AccountControllerTestRestTemplate {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenGetCuentasDeUnUsuarioWhenExistsThenExist() throws Exception {
        HttpHeaders headers = new HttpHeaders();
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
