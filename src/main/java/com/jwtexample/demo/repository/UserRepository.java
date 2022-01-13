package com.jwtexample.demo.repository;

import com.jwtexample.demo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser findAppUserByUsername(String username);


}
