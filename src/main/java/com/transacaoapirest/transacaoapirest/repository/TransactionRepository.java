package com.transacaoapirest.transacaoapirest.repository;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByuserSender(Account userSender);
}
