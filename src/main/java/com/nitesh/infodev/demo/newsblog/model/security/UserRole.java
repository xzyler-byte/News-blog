package com.nitesh.infodev.demo.newsblog.model.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nitesh.infodev.demo.newsblog.model.User;

@Entity
@Table(name = "userRole")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uerRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public long getUerRoleId() {
		return uerRoleId;
	}

	public void setUerRoleId(long uerRoleId) {
		this.uerRoleId = uerRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRole() {
		super();
	}

}
