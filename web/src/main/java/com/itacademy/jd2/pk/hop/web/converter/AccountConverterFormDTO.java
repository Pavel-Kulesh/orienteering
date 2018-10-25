package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Component
public class AccountConverterFormDTO implements Function<RegFormDTO, IUserAccount> {
	private IUserAccountService userAccountService;

	@Autowired
	public AccountConverterFormDTO(IUserAccountService userAccountService) {
		super();
		this.userAccountService = userAccountService;
	}

	@Override
	public IUserAccount apply(RegFormDTO dto) {
		IUserAccount entity = userAccountService.createEntity();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setRole(Role.USER);
		return entity;
	}

}
