package com.nitesh.infodev.demo.newsblog.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private Set<News> newses;

	public User() {
	}

	public User(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<News> getNewses() {
		return newses;
	}

	public void setNewses(Set<News> newses) {
		this.newses = newses;
	}
}
