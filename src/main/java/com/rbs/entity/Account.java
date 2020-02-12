package com.rbs.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountnumber;
    private Double balance;

    public Account() {

    }

    public Account(Long id, String accountnumber, Double balance) {
        this.id = id;
        this.accountnumber = accountnumber;
        this.balance = balance;
    }



    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "accountnumber")
    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountNumber) {
        this.accountnumber = accountNumber;
    }
    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountnumber='" + accountnumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
