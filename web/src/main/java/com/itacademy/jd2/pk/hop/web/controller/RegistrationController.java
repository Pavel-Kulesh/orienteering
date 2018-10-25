package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;
import com.itacademy.jd2.pk.hop.web.converter.AccountConverterFormDTO;
import com.itacademy.jd2.pk.hop.web.converter.CustomerConverterFormDTO;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

	private ICustomerService customerService;
	private IUserAccountService userAccountService;
	private AccountConverterFormDTO accountConverterFormDTO;
	private CustomerConverterFormDTO customerConverterFormDTO;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	public RegistrationController(ICustomerService customerService, IUserAccountService userAccountService,
			AccountConverterFormDTO accountConverterFormDTO, CustomerConverterFormDTO customerConverterFormDTO) {
		super();
		this.customerService = customerService;
		this.userAccountService = userAccountService;
		this.accountConverterFormDTO = accountConverterFormDTO;
		this.customerConverterFormDTO = customerConverterFormDTO;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", new RegFormDTO());

		return new ModelAndView("registration", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final RegFormDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		} else {

			final IUserAccount userAccount = accountConverterFormDTO.apply(formModel);
			userAccountService.save(userAccount);

				final ICustomer customer = customerConverterFormDTO.apply(formModel);

				customer.setId(userAccount.getId());
				customerService.save(customer);
			LOGGER.info("create userAccont + customer sucsess");
			return "redirect:/login";
		}
	}

}
