package com.itacademy.jd2.pk.hop.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ExtendedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String userName;

	private Integer id;

	public ExtendedUsernamePasswordAuthenticationToken(final Integer id, final Object principal,
			final Object credentials, final Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		setId(id);
		setUserName((String) principal);
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String principal) {
		this.userName = principal;
	}

}
