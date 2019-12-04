package com.nitesh.infodev.demo.newsblog.model.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private final String authority;

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
