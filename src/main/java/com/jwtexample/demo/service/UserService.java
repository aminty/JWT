package com.jwtexample.demo.service;

import com.jwtexample.demo.domain.AppUser;
import com.jwtexample.demo.domain.Role;

import java.util.Set;

public interface UserService  {

    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToAppUser(String username,String roleName);

    AppUser getUser(String username);

    Set<AppUser> getAppUsers();


}
