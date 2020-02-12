package com.rbs.api;

import com.rbs.Exception.AccountNotFoundException;
import com.rbs.Exception.InsufficientFundException;
import com.rbs.model.Transaction;
import com.rbs.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService ts;

    /**
     * transfer amount from one account to another account
     * @param transaction
     * @return
     * @throws InsufficientFundException
     * @throws AccountNotFoundException
     */
    @PostMapping("/sendAmount")
    public ResponseEntity<Object> transferAmount(@RequestBody Transaction transaction) throws InsufficientFundException, AccountNotFoundException {
        return new ResponseEntity(ts.transferAmount(transaction), HttpStatus.OK);
    }

    /**
     * List all account number
     * @return
     * @throws InsufficientFundException
     * @throws AccountNotFoundException
     */
    @GetMapping("/all")
    public ResponseEntity<Object> allAccounts()  {
        return new ResponseEntity(ts.getAllAccounts(), HttpStatus.OK);
    }

    /**
     * add new account number
     * @param accountnumber
     * @param amount
     * @return ResponseEntity
     */
    @RequestMapping("/add/{accountnumber}/{amount}")
    public ResponseEntity<Object> addAccounts(@PathVariable("accountnumber") String accountnumber,@PathVariable("amount") Double amount) {
        return new ResponseEntity(ts.addAccount(accountnumber,amount), HttpStatus.OK);
    }
}
