package com.myonlineshopping.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private int balance;
    private String opening_date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer owner;




}
