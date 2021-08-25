package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.Transaction;
import com.transacaoapirest.transacaoapirest.model.TransictionStatus;
import com.transacaoapirest.transacaoapirest.repository.AccountRepository;
import com.transacaoapirest.transacaoapirest.repository.TransactionRepository;
import com.transacaoapirest.transacaoapirest.service.AccountService;
import com.transacaoapirest.transacaoapirest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class TransactionServiceImplementation implements TransactionService {
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        Optional<Account> userSender = accountService.getAccountById(transaction.getUserSender().getId());
        Optional<Account> userReciever = accountService.getAccountById(transaction.getUserReciever().getId());
        if(transaction.getUserSender().getUser().getRoles().toString().equals("ROLE_CLIENTE")
                && transaction.getUserReciever().getUser().getRoles().toString().equals("ROLE_LOJISTA")
                && userSender.isPresent() && userReciever.isPresent()){
            if(transaction.getUserSender().getBalance() >= transaction.getTransictionValue()){
                userSender.get().setBalance(userSender.get().getBalance() - transaction.getTransictionValue());
                accountRepository.save(userSender.get());
                userReciever.get().setBalance(userReciever.get().getBalance() + transaction.getTransictionValue());
                accountRepository.save(userReciever.get());
                transaction.setTransictionDate(LocalDate.now());
                transaction.setTransictionStatus(TransictionStatus.DONE);
                transactionRepository.save(transaction);
            }
            else{
                transaction.setTransictionDate(LocalDate.now());
                transaction.setTransictionStatus(TransictionStatus.ERROR);
                transactionRepository.save(transaction);
            }
        }
        else{
            transaction.setTransictionDate(LocalDate.now());
            transaction.setTransictionStatus(TransictionStatus.ERROR);
            transactionRepository.save(transaction);
        }

        return transaction;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long idTransaction) {

        return Optional.empty();
    }

    @Override
    public Transaction getByUserSender(Account userSender) {
        return transactionRepository.findByuserSender(userSender);
    }

    @Override
    public Transaction getByUserReciever(Account userReciever) {
        return transactionRepository.findByuserSender(userReciever);
    }


}
