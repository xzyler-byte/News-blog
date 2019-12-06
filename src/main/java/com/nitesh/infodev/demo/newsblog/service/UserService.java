package com.nitesh.infodev.demo.newsblog.service;

import java.util.List;

import com.nitesh.infodev.demo.newsblog.model.User;

public interface UserService {

	User findByUsername(String username);

	User findById(Long id);

	User save(User user);

	List<User> getUsers();
}
