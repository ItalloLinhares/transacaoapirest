package com.transacaoapirest.transacaoapirest.repository;

import com.transacaoapirest.transacaoapirest.model.Account;
import com.transacaoapirest.transacaoapirest.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByid(Long id);
    Optional<Account> findByuser(Optional<AppUser> user);
}
