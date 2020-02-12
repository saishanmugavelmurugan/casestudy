package com.rbs.service;

import com.rbs.Exception.AccountNotFoundException;
import com.rbs.Exception.InsufficientFundException;
import com.rbs.entity.Account;
import com.rbs.entity.TransactionRepository;
import com.rbs.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionServiceTest {
    @MockBean
    TransactionRepository tr;
    @Autowired
    TransactionService ts;
    @Test
    public void transferAmount_ok() throws InsufficientFundException, AccountNotFoundException {
        Transaction mockTransaction = new Transaction("rbs123456", "rbs123457",Double.valueOf(20));
        when(tr.findByAccountNumber("rbs123456")).thenReturn(new Account(Long.valueOf(1),"rbs123456",Double.valueOf(1000)));
        when(tr.findByAccountNumber("rbs123457")).thenReturn(new Account(Long.valueOf(2),"rbs123457",Double.valueOf("2000")));
        assertEquals("200",ts.transferAmount(mockTransaction).getStatusCode());
    }
    @Test
    public void transferAmount_accountnumber_notavialble() throws InsufficientFundException, AccountNotFoundException {

        when(tr.findByAccountNumber("rbs123456")).thenReturn(new Account(Long.valueOf(1),"rbs123456",Double.valueOf(1000)));
        when(tr.findByAccountNumber("rbs123457")).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {ts.transferAmount(new Transaction("rbs123456", "rbs123457",Double.valueOf(20)));});
        String expectedMessage = "Source or destination account not found";
        String actualMessage = exception.getMessage();

        assertEquals(true, actualMessage.contains(expectedMessage));
    }
    @Test
    public void transferAmount_amount_notavialble() throws InsufficientFundException, AccountNotFoundException {
        when(tr.findByAccountNumber("rbs123456")).thenReturn(new Account(Long.valueOf(1),"rbs123456",Double.valueOf(10)));
        when(tr.findByAccountNumber("rbs123457")).thenReturn(new Account(Long.valueOf(2),"rbs123457",Double.valueOf("2000")));

        Exception exception = assertThrows(InsufficientFundException.class, () -> {ts.transferAmount(new Transaction("rbs123456", "rbs123457",Double.valueOf(20)));});
        String expectedMessage = "Source account has insufficient fund";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertEquals(true, actualMessage.contains(expectedMessage));
    }
}
