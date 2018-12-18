package com.itacademy.jd2.pk.hop.web.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ExtendedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String email;

	private Integer id;
	private String name;
	private String surname;
	private String role;

	public ExtendedUsernamePasswordAuthenticationToken(final Integer id, final Object principal,
			final Object credentials, final Collection<? extends GrantedAuthority> authorities, final String name,
			final String surname, final String role) {
		super(principal, credentials, authorities);

		this.id = id;
		this.name = name;
		this.surname = surname;
		this.role = role;
		email = (String) principal;

	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getRole() {
		return role;
	}

}
