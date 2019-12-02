package com.nitesh.infodev.demo.newsblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nitesh.infodev.demo.newsblog.service.impl.UserServiceImpl;

@SpringBootApplication
public class NewsBlogApplication /* implements CommandLineRunner */ {

	@Autowired
	UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(NewsBlogApplication.class, args);
	}
	/*
	 * @Override public void run(String... args) throws Exception { User user1 = new
	 * User(); user1.setUsername("John"); user1.setPassword("Adams");
	 * user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
	 * Set<UserRole> userRoles = new HashSet<>(); Role role1 = new Role();
	 * role1.setRoleId(1); role1.setName("ROLE_ADMIN"); userRoles.add(new
	 * UserRole(user1, role1));
	 * 
	 * userService.createUser(user1, userRoles); }
	 */

}
