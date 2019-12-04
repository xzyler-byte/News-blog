package com.nitesh.infodev.demo.newsblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.infodev.demo.newsblog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
