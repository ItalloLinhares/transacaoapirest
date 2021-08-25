package com.transacaoapirest.transacaoapirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private AppUser user;
    private float balance;
}
