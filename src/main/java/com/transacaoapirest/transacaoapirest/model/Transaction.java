package com.transacaoapirest.transacaoapirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Account userSender;
    @ManyToOne
    private Account userReciever;
    private float transictionValue;
    private LocalDate transictionDate;
    private TransictionStatus transictionStatus;
}
