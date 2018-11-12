package com.itacademy.jd2.pk.hop.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	private IUserAccountService userAccountService;
	private ICustomerService customerService;

	@Autowired
	public CustomAuthenticationProvider(IUserAccountService userAccountService, ICustomerService customerService) {
		super();
		this.userAccountService = userAccountService;
		this.customerService = customerService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String email = (String) authentication.getPrincipal();
		final String password = authentication.getCredentials() + "";

		LOGGER.info("Entered: Username={}, Password={}.", email);

		IUserAccount user = userAccountService.getByEmail(email);

		if (user == null) {
			LOGGER.error("CALL THE ADMIN!");

			throw new BadCredentialsException("1000");
		}

		if (!user.getEmail().equals(email)) {
			throw new BadCredentialsException("1000");
		}

		if (!user.getPassword().equals(DigestUtils.md5Hex(password))) {
			throw new BadCredentialsException("1000");
		}

		final int userId = user.getId();

		List<String> userRoles = new ArrayList<>();

		String role = user.getRole().name();
		userRoles.add("ROLE_" + role);

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		ICustomer customer = customerService.get(userId);
		String name = customer.getName();
		String surname = customer.getSurname();
		return new ExtendedUsernamePasswordAuthenticationToken(userId, email, password, authorities, name, surname,
				role);

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}