package com.Medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Medicare.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
