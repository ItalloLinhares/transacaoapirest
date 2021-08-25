package com.transacaoapirest.transacaoapirest.dto;

import com.transacaoapirest.transacaoapirest.model.DocumentType;
import com.transacaoapirest.transacaoapirest.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUserDto {
        private String name;
        private String username;
    }
