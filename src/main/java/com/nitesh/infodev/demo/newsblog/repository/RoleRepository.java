package com.nitesh.infodev.demo.newsblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.infodev.demo.newsblog.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByname(String name);
}
