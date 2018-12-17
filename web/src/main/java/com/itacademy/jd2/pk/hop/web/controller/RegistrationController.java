package com.itacademy.jd2.pk.hop.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.service.ICityService;
import com.itacademy.jd2.pk.hop.service.ICountryService;
import com.itacademy.jd2.pk.hop.service.IRegisterService;
import com.itacademy.jd2.pk.hop.web.converter.AccountConverterFormFromDTO;
import com.itacademy.jd2.pk.hop.web.converter.CityToDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.CountryToDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.CustomerConverterRegFormFromDTO;
import com.itacademy.jd2.pk.hop.web.dto.CityDTO;
import com.itacademy.jd2.pk.hop.web.dto.CountryDTO;
import com.itacademy.jd2.pk.hop.web.dto.RegFormDTO;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

	private AccountConverterFormFromDTO accountConverterFormDTO;
	private CustomerConverterRegFormFromDTO customerConverterFormDTO;
	private IRegisterService registerService;
	private ICityService cityService;
	private ICountryService countryService;
	private CountryToDTOConverter countryToDTO;
	private CityToDTOConverter cityToDTO;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	public RegistrationController(AccountConverterFormFromDTO accountConverterFormDTO,
			CustomerConverterRegFormFromDTO customerConverterFormDTO, IRegisterService registerService,
			ICityService cityService, ICountryService countryService, CountryToDTOConverter countryToDTO,
			CityToDTOConverter cityToDTO) {
		super();
		this.accountConverterFormDTO = accountConverterFormDTO;
		this.customerConverterFormDTO = customerConverterFormDTO;
		this.registerService = registerService;
		this.cityService = cityService;
		this.countryService = countryService;
		this.countryToDTO = countryToDTO;
		this.cityToDTO = cityToDTO;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest req) {
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", new RegFormDTO());
		String url = req.getHeader("referer");
		hashMap.put("url", url);
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
			return "login";
		}
	}

	private void loadComboboxesModels(final Map<String, Object> hashMap) {

		final List<ICity> cities = cityService.getAll();

		final Map<Integer, String> citiesMap = cities.stream().collect(Collectors.toMap(ICity::getId, ICity::getName));

		hashMap.put("cityChoices", citiesMap);

	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<List<CountryDTO>> getCountries() {
		List<ICountry> entities = countryService.getAll();
		List<CountryDTO> countries = entities.stream().map(countryToDTO).collect(Collectors.toList());

		return new ResponseEntity<List<CountryDTO>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> getCities(
			@RequestParam(name = "countryId", required = true) final Integer countryId) {

		List<ICity> citiesByCountry = cityService.getByCountry(countryId);
		final List<CityDTO> cities = citiesByCountry.stream().map(cityToDTO).collect(Collectors.toList());

		return new ResponseEntity<List<CityDTO>>(cities, HttpStatus.OK);
	}

}
