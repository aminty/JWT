package com.jwtexample.demo.service.impl;

import com.jwtexample.demo.domain.AppUser;
import com.jwtexample.demo.domain.Role;
import com.jwtexample.demo.repository.RoleRepository;
import com.jwtexample.demo.repository.UserRepository;
import com.jwtexample.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        AppUser user=userRepository.findAppUserByUsername(username);
        Role role=roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return userRepository.findAll();
    }
}
