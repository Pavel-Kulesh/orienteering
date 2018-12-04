package com.itacademy.jd2.pk.hop.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.itacademy.jd2.pk.hop.dao.api.filter.AbstractFilter;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.SortDTO;
import com.itacademy.jd2.pk.hop.web.security.AuthHelper;
import com.itacademy.jd2.pk.hop.web.security.ExtendedUsernamePasswordAuthenticationToken;

public abstract class AbstractController<DTO> {
	protected GridStateDTO getListDTO(final HttpServletRequest req) {
		final String sessionModelName = getClass().getSimpleName() + "_GRID_STATE";

		GridStateDTO gridState = (GridStateDTO) req.getSession().getAttribute(sessionModelName);
		if (gridState == null) {
			gridState = new GridStateDTO();
			req.getSession().setAttribute(sessionModelName, gridState);
		}
		req.setAttribute(GridStateDTO.GRID_STATE_SESSION_KEY, gridState);
		return gridState;
	}

	protected void prepareFilter(GridStateDTO gridState, AbstractFilter filter) {
		filter.setLimit(gridState.getItemsPerPage());
		int offset = gridState.getItemsPerPage() * (gridState.getPage() - 1);
		filter.setOffset(gridState.getTotalCount() < offset ? 0 : offset);

		final SortDTO sortModel = gridState.getSort();
		if (sortModel != null) {
			filter.setSortColumn(sortModel.getColumn());
			filter.setSortOrder(sortModel.isAscending());
		}
	}

	protected Integer getCustomerId() {
		Integer customerId = AuthHelper.getLoggedUserId();
		return customerId;
	}

	protected String getLoginRole() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context.getAuthentication().getPrincipal().equals("anonymousUser")) {
			return null;

		}
		ExtendedUsernamePasswordAuthenticationToken authentication = (ExtendedUsernamePasswordAuthenticationToken) context
				.getAuthentication();

		String role = authentication.getRole();
		return role;
	}
}