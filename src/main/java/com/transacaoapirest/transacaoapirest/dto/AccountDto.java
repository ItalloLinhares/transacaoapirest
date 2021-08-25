package com.transacaoapirest.transacaoapirest.dto;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccountDto {
    public  AccountDto(Account account){
        this.user = account.getUser();
        this.balance = account.getBalance();
    }
    private AppUser user;
    private float balance;
}
