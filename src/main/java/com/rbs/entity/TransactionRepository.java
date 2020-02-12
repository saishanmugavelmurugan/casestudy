package com.rbs.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Account, Long> {
    @Query("SELECT new Account(id,accountnumber,balance) FROM Account t where t.accountnumber = ?1")
    Account findByAccountNumber(String accountNumber);
    @Modifying
    @Query("Update Account set balance=?1 where accountnumber = ?2")
    void updateAmount(Double amount,String accountNumber);
}
