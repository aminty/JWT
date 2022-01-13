package com.jwtexample.demo.repository;

import com.jwtexample.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName (String name);

}
