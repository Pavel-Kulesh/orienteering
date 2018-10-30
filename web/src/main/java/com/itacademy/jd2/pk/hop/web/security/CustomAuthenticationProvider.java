package com.itacademy.jd2.pk.hop.web.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	private IUserAccountService userAccountService;

	@Autowired
	public CustomAuthenticationProvider(IUserAccountService userAccountService) {
		super();
		this.userAccountService = userAccountService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String username = (String) authentication.getPrincipal();
		final String password = authentication.getCredentials() + "";

		LOGGER.info("Entered: Username={}, Password={}.", username, password);

		IUserAccount user = userAccountService.getByEmail(username);

		if (user == null) {
			LOGGER.error(
					"YOU HAVE SEVERAL IDENTICAL USERS IN THE TABLE! YOU MUST CHANGE THE DATABASE! THE EMAIL FIELD MUST BE UNIQUE! CALL THE ADMIN!");

			throw new BadCredentialsException("1000");
		}

		if (!user.getEmail().equals(username)) {
			throw new BadCredentialsException("1000");
		}

		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = user.getId();

		List<String> userRoles = new ArrayList<>();

		userRoles.add("ROLE_" + user.getRole().name());

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		return new ExtendedUsernamePasswordAuthenticationToken(userId, username, password, authorities);

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}