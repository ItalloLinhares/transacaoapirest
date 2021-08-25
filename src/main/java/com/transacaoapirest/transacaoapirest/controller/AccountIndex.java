package com.transacaoapirest.transacaoapirest.controller;

import com.transacaoapirest.transacaoapirest.dto.AccountDto;
import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.repository.AccountRepository;
import com.transacaoapirest.transacaoapirest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController @RequiredArgsConstructor @RequestMapping("/account")
public class AccountIndex {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @PostMapping("/save")
    public ResponseEntity<Account>saveAccount(@RequestBody Long idUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/account/save").toString());
        return ResponseEntity.created(uri).body(accountService.saveAccount(idUser));
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<AccountDto>getAccountById(@PathVariable Long idAccount){
        Optional<Account> account = accountService.getAccountById(idAccount);
        if(account.isPresent()){
            return ResponseEntity.ok(new AccountDto(account.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<AccountDto>updateAccountById(@PathVariable Long idAccount, @RequestBody Account account){
        if(accountRepository.findById(account.getId()).isPresent()){
            return ResponseEntity.ok(new AccountDto(accountService.updateAccountById(account)));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<AccountDto>deleteAccountById(@PathVariable Long idAccount){
        boolean deleted = accountService.deleteAccountById(idAccount);
        if(deleted == true){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByUser/{idUser}")
    public ResponseEntity<AccountDto>getAccountByUser(@PathVariable Long idUser){
        Optional<Account> account = accountService.getAccountByUser(idUser);
        if(account.isPresent()){
            return ResponseEntity.ok(new AccountDto(account.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateBalanceById/{id}")
    public ResponseEntity<AccountDto>updateBalanceAccountById(@PathVariable Long idAccount, @RequestBody float balance){
        if(accountRepository.findById(idAccount).isPresent()){
           return ResponseEntity.ok(new AccountDto(accountService.updateBalanceAccountById(idAccount, balance)));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
