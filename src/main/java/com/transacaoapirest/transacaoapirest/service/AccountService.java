package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.Account;

import java.util.Optional;

public interface AccountService {
    Account saveAccount(Long idUser);
    Optional<Account>  getAccountById(Long id);
    Optional<Account> getAccountByUser(Long idUser);
    Account updateAccountById(Account account);
    Account updateBalanceAccountById(Long idAccount, float balance);
    boolean deleteAccountById(Long id);

}
