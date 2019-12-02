package com.nitesh.infodev.demo.newsblog.service;

import java.util.List;
import java.util.Set;

import com.nitesh.infodev.demo.newsblog.model.User;
import com.nitesh.infodev.demo.newsblog.model.security.UserRole;

public interface UserService {

	User findByUsername(String username);

	User findById(Long id);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);

	List<User> getUsers();
	

}
