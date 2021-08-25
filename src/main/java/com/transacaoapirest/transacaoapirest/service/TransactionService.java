package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(Long idTransaction);
    Transaction getByUserSender(Account userSender);
    Transaction getByUserReciever(Account userReciever);
}
