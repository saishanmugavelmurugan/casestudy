package com.rbs.service;

import com.rbs.Exception.AccountNotFoundException;
import com.rbs.Exception.InsufficientFundException;
import com.rbs.entity.TransactionRepository;
import com.rbs.entity.Account;
import com.rbs.model.RestApiStatus;
import com.rbs.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    /**
     *
     * @return Iterable<Account>
     */
    @Override
    public Iterable<Account> getAllAccounts() {
        return transactionRepository.findAll();
    }

    /**
     * add account
     * @param accountnumber
     * @param amount
     * @return RestApiStatus
     */
    @Override
    public RestApiStatus addAccount(String accountnumber, Double amount) {
        Account account=new Account();
        account.setAccountnumber(accountnumber);
        account.setBalance(amount);
        transactionRepository.save(account);
        return new RestApiStatus(HttpStatus.OK,"200","success","Account successfull.");
    }

    /**
     * transfer account
     * @param ts
     * @return RestApiStatus
     * @throws AccountNotFoundException
     * @throws InsufficientFundException
     */
    @Override
    @Transactional
    public RestApiStatus transferAmount(Transaction ts) throws AccountNotFoundException, InsufficientFundException {
        Account source=transactionRepository.findByAccountNumber(ts.getSourceAccountNumber());
        Account destination=transactionRepository.findByAccountNumber(ts.getDestinationAccountNumber());
        if(source!=null && destination!=null){
            if(isAmountTransferable(source.getBalance(),ts.getAmount())){
                transactionRepository.updateAmount(source.getBalance()-ts.getAmount(),source.getAccountnumber());
                transactionRepository.updateAmount(source.getBalance()+ts.getAmount(),destination.getAccountnumber());
            }else{
                throw new InsufficientFundException("Source account has insufficient fund");
            }
        }else{
            throw new AccountNotFoundException("Source or destination account not found");
        }
        return new RestApiStatus(HttpStatus.OK,"200","success","Transaction successfull.");
    }
    public boolean isAmountTransferable(Double source,Double requestAmount ){
        if(source>=requestAmount){
            return true;
        }
        return false;
    }
}
