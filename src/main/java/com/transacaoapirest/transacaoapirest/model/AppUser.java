package com.transacaoapirest.transacaoapirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private Long numberDocument;
    private DocumentType documentType;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayDeque<>();


}
