package com.transacaoapirest.transacaoapirest.controller;

import com.transacaoapirest.transacaoapirest.dto.AccountDto;
import com.transacaoapirest.transacaoapirest.dto.TransactionDto;
import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.Transaction;
import com.transacaoapirest.transacaoapirest.repository.AccountRepository;
import com.transacaoapirest.transacaoapirest.repository.TransactionRepository;
import com.transacaoapirest.transacaoapirest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController @RequiredArgsConstructor @RequestMapping("/transaction")
public class TransactionIndex {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/transaction/save").toString());
        return ResponseEntity.created(uri).body(transactionService.saveTransaction(transaction));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long idTransaction){
        Optional<Transaction> transaction = transactionService.getTransactionById(idTransaction);
        if(transaction.isPresent()){
            return ResponseEntity.ok(new TransactionDto(transaction.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByUserSender/{id}")
    public ResponseEntity<TransactionDto> getByIdUserSender(@PathVariable Long idUserSender){
        Optional<Account> userSender = accountRepository.findById(idUserSender);
        if(userSender.isPresent()){
            return ResponseEntity.ok(new TransactionDto(transactionService.getByUserSender(userSender.get())));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByUserReciever/{id}")
    public ResponseEntity<TransactionDto> getByIdUserReciever(@PathVariable Long idUserReciever){
        Optional<Account> userReciever = accountRepository.findById(idUserReciever);
        if(userReciever.isPresent()){
            return ResponseEntity.ok(new TransactionDto(transactionService.getByUserSender(userReciever.get())));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
