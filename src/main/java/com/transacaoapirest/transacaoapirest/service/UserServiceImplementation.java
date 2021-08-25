package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.model.Role;
import com.transacaoapirest.transacaoapirest.repository.AppUserRepository;
import com.transacaoapirest.transacaoapirest.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    private  final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByusername(username);
        if(appUser == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser appUser = appUserRepository.findByusername(username);
        Role role = roleRepository.findByroleName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepository.findByusername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return appUserRepository.findAll();
    }





}
