package com.transacaoapirest.transacaoapirest.service;

import com.transacaoapirest.transacaoapirest.model.AppUser;
import com.transacaoapirest.transacaoapirest.model.Role;

import java.util.List;

public interface UserService {
    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getUsers();
}
