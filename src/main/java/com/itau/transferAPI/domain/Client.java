package com.itau.transferAPI.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Client {

    private UUID id;
    private String name;
    private String accountNumber;
    private BigDecimal balance;

    public Client() {
    }

    public Client(UUID id,
                  String name,
                  String accountNumber,
                  BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}