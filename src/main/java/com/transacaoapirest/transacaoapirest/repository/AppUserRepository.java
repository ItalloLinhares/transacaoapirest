package com.transacaoapirest.transacaoapirest.repository;

import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByusername(String username);
    AppUser findByid(Long id);
}
