package com.jwtexample.demo.service.impl;

import com.jwtexample.demo.domain.AppUser;
import com.jwtexample.demo.domain.Role;
import com.jwtexample.demo.repository.RoleRepository;
import com.jwtexample.demo.repository.UserRepository;
import com.jwtexample.demo.service.UserService;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /** This method implemented from UserDetailedService
    * and use for that , how to use from  user info in database */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=getUser(username);
        if (user==null){
            log.error("User not found in database!");
            throw new UsernameNotFoundException("User not found in database!");
        }else{
            log.info("User found in database: {}",username);
        }
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));


        /**org.springframework.security.core.userdetails.User()*/
        return new User(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public AppUser saveUser(AppUser user) {
        log.info("user {} saved successfully", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("role {} saved successfully", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        AppUser user = userRepository.findAppUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        log.info("role {} added to {} user", roleName, username);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("find {} user", username);
        return userRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("get all user");
        return userRepository.findAll();
    }

}
