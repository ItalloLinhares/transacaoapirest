package com.transacaoapirest.transacaoapirest.dto;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.model.Transaction;
import com.transacaoapirest.transacaoapirest.model.TransictionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class TransactionDto {

    public TransactionDto(Transaction transaction){
        this.idTransactionDto = transaction.getId();
        this.userSender = transaction.getUserSender();
        this.userReciever = transaction.getUserReciever();
        this.transictionValue = transaction.getTransictionValue();
        this.transictionDate = transaction.getTransictionDate();
        this.transictionStatus = transaction.getTransictionStatus();
    }


    private Long idTransactionDto;
    private Account userSender;
    private Account userReciever;
    private float transictionValue;
    private LocalDate transictionDate;
    private TransictionStatus transictionStatus;
}
