package com.itacademy.jd2.pk.hop.web.controller;

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
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.service.ICustomerService;
import com.itacademy.jd2.pk.hop.web.converter.CustomerFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.CustomerToDTOConterter;
import com.itacademy.jd2.pk.hop.web.dto.CustomerDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.ListDTO;

@Controller
@RequestMapping(value = "/participant")
public class participantController extends AbstractController<CustomerDTO> {

	private ICustomerService customerService;

	private CustomerToDTOConterter toDTOConverter;
	private CustomerFromDTOConverter fromDTOConverter;

	@Autowired
	public participantController(ICustomerService customerService, CustomerToDTOConterter toDTOConverter,
			CustomerFromDTOConverter fromDTOConverter) {
		super();
		this.customerService = customerService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final ListDTO<CustomerDTO> listDTO = getListDTO(req);
		listDTO.setPage(pageNumber);
		listDTO.setSort(sortColumn);

		final CustomerFilter filter = new CustomerFilter();
		prepareFilter(listDTO, filter);

		final List<ICustomer> entities = customerService.find(filter);
		listDTO.setList(entities.stream().map(toDTOConverter).collect(Collectors.toList()));
		listDTO.setTotalCount(customerService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put(ListDTO.LIST_MODEL_ATTRIBUTE, listDTO);
		return new ModelAndView("participant.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICustomer newEntity = customerService.createEntity();
		CustomerDTO dto = toDTOConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("participant.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CustomerDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "participant.edit";
		} else {
			final ICustomer entity = fromDTOConverter.apply(formModel);
			customerService.save(entity);
			return "redirect:/participant";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		customerService.delete(id);
		return "redirect:/participant";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICustomer dbModel = customerService.get(id);
		final CustomerDTO dto = toDTOConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("participant.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CustomerDTO dto = toDTOConverter.apply(customerService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("participant.edit", hashMap);
	}

}