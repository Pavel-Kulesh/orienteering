package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.IRegisterService;
import com.itacademy.jd2.pk.hop.web.converter.AccountConverterFormDTO;
import com.itacademy.jd2.pk.hop.web.converter.CustomerConverterFormDTO;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

	private AccountConverterFormDTO accountConverterFormDTO;
	private CustomerConverterFormDTO customerConverterFormDTO;
	private IRegisterService registerService;
	private ICityService cityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	public RegistrationController(AccountConverterFormDTO accountConverterFormDTO,
			CustomerConverterFormDTO customerConverterFormDTO, IRegisterService registerService,
			ICityService cityService) {
		super();
		this.accountConverterFormDTO = accountConverterFormDTO;
		this.customerConverterFormDTO = customerConverterFormDTO;
		this.registerService = registerService;
		this.cityService = cityService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", new RegFormDTO());
		loadComboboxesModels(hashMap);
		return new ModelAndView("registration", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final RegFormDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "registration";
		} else {

			final IUserAccount userAccount = accountConverterFormDTO.apply(formModel);
			final ICustomer customer = customerConverterFormDTO.apply(formModel);
			registerService.saveRegisterData(customer, userAccount);

			LOGGER.info("create userAccont + customer sucsess");
			return "redirect:/login";
		}
	}

	private void loadComboboxesModels(final Map<String, Object> hashMap) {

		final List<ICity> cities = cityService.getAll();

		final Map<Integer, String> citiesMap = cities.stream().collect(Collectors.toMap(ICity::getId, ICity::getName));

		hashMap.put("cityChoices", citiesMap);

	}

}
