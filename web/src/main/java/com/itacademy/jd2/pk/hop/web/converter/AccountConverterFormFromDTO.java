package com.itacademy.jd2.pk.hop.web.converter;

import java.util.function.Function;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Component
public class AccountConverterFormFromDTO implements Function<RegFormDTO, IUserAccount> {
	private IUserAccountService userAccountService;

	@Autowired
	public AccountConverterFormFromDTO(IUserAccountService userAccountService) {
		super();
		this.userAccountService = userAccountService;
	}

	@Override
	public IUserAccount apply(RegFormDTO dto) {
		IUserAccount entity = userAccountService.createEntity();
		entity.setEmail(dto.getEmail());
		entity.setPassword(DigestUtils.md5Hex(dto.getPassword()));
		entity.setRole(Role.USER);
		return entity;
	}

}
