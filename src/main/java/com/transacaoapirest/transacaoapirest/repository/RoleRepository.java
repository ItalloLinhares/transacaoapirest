package com.transacaoapirest.transacaoapirest.repository;

import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
        Role findByroleName(String username);
    }