package com.rbs.service;

import com.rbs.Exception.AccountNotFoundException;
import com.rbs.Exception.InsufficientFundException;
import com.rbs.entity.Account;
import com.rbs.model.RestApiStatus;
import com.rbs.model.Transaction;

public interface TransactionService {
    public Iterable<Account> getAllAccounts();
    public RestApiStatus transferAmount(Transaction ts) throws AccountNotFoundException, InsufficientFundException;
    public RestApiStatus addAccount(String accountnumber, Double amount);
}
