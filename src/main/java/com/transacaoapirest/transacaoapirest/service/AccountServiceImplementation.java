package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.repository.AccountRepository;
import com.transacaoapirest.transacaoapirest.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AccountServiceImplementation implements AccountService{
    private final AccountRepository accountRepository;
    private final AppUserRepository appUserRepository;

    @Override
    @Transactional
    public Account saveAccount(Long idUser){
        Account account = new Account();
        account.setUser(appUserRepository.findByid(idUser));
        account.setBalance(0);
        log.info("Saving new account in {} profile to the database", account.getUser().getName());
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account>  getAccountById(Long idAccount){
        log.info("fetching acount with id {}", idAccount);
        return accountRepository.findByid(idAccount);
    }

    @Override
    @Transactional
    public Account updateAccountById(Account account) {
        return accountRepository.save(account);
    }


    @Override
    @Transactional
    public boolean deleteAccountById(Long idAccount) {
        Optional<Account> account = accountRepository.findByid(idAccount);
        if (account.isPresent()) {
            log.info("deleting acount with id {}", idAccount);
            accountRepository.delete(account.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Account> getAccountByUser(Long idUser) {
        log.info("fetching the user with id: {}", idUser);
        Optional<AppUser> user = appUserRepository.findById(idUser);

        if (user.isPresent()){
            log.info("fetching all acounts with user {}", user);
            return accountRepository.findByuser(user);
        }
        else{ return null; }
    }


    @Override
    public Account updateBalanceAccountById(Long idAccount, float balance) {
        Optional<Account> account = accountRepository.findByid(idAccount);
        account.get().setBalance(balance);
        return account.get();
    }

}
