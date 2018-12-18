package com.itacademy.jd2.pk.hop.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.service.IUserAccountService;
import com.itacademy.jd2.pk.hop.web.converter.CustomerFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.CustomerToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/participant")
public class ParticipantController extends AbstractController<CustomerDTO> {

	private ICustomerService customerService;
	private IUserAccountService userAccountService;
	private CustomerToDTOConverter toDTOConverter;
	private CustomerFromDTOConverter fromDTOConverter;

	@Autowired
	public ParticipantController(ICustomerService customerService, CustomerToDTOConverter toDTOConverter,
			CustomerFromDTOConverter fromDTOConverter, IUserAccountService userAccountService) {
		super();
		this.customerService = customerService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
		this.userAccountService = userAccountService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req, @RequestParam(name = "page", required = false) Integer pageNumber,
			@RequestParam(name = "sort", required = false) String sortColumn) {

		GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		CustomerFilter filter = new CustomerFilter();
		prepareFilter(gridState, filter);

		List<ICustomer> entities = customerService.find(filter);
		List<CustomerDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
		String currentLoginRole = getLoginRole();
		Integer currentCustomerId = getCustomerId();

		gridState.setTotalCount(customerService.getCount(filter));

		HashMap<String, Object> models = new HashMap<>();
		models.put("gridItem", dtos);

		Integer currentCustomer = currentCustomerId;
		models.put("currentCustomer", currentCustomer);

		models.put("isAdmin", "ADMIN".equals(currentLoginRole));
		return new ModelAndView("participant.list", models);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") CustomerDTO formModel, BindingResult result) {
		if (result.hasErrors()) {
			return "participant.edit";
		} else {
			ICustomer entity = fromDTOConverter.apply(formModel);

			customerService.save(entity);
			return "redirect:/participant";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) Integer id) {
		customerService.delete(id);
		userAccountService.delete(id);
		return "redirect:/participant";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest req) {
		ICustomer dbModel = customerService.get(id);
		CustomerDTO dto = toDTOConverter.apply(dbModel);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		req.getAttribute("referer");
		return new ModelAndView("participant.info", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest req) {

		CustomerDTO dto = toDTOConverter.apply(customerService.get(id));

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadComboboxesModels(hashMap);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		return new ModelAndView("participant.edit", hashMap);
	}

	private void loadComboboxesModels(Map<String, Object> hashMap) {
		List<Role> customerRolesList = Arrays.asList(Role.values());
		Map<String, String> eventTypesMap = customerRolesList.stream()
				.collect(Collectors.toMap(Role::name, Role::name));

		hashMap.put("roleChoices", eventTypesMap);

	}

}
