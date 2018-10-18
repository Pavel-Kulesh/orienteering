package com.itacademy.jd2.pk.hop.web.dto.list;

import java.util.List;

public class ListDTO<T> {

	public static final String LIST_MODEL_ATTRIBUTE = "listDTO";

	private List<T> list;

	private SortDTO sort;

	private long pageCount;

	private int page = 1;

	private int itemsPerPage;

	private long totalCount;

	public ListDTO(final int itemsPerPage) {
		super();
		this.itemsPerPage = itemsPerPage;
	}

	public ListDTO() {
		this(4);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(final List<T> list) {
		this.list = list;
	}

	public SortDTO getSort() {
		return sort;
	}

	private void setSort(final SortDTO sort) {
		this.sort = sort;
	}

	public void setSort(final String sort) {
		if (sort == null) {
			return;
		}

		final String[] sortParams = sort.split(":");
		// unsafe operation below but assumes that JSP doesn't have bugs
		if (sortParams.length == 1) {
			setSort(new SortDTO(sortParams[0]));
		} else {
			setSort(new SortDTO(sortParams[0], "asc".equals(sortParams[1])));
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(final Integer pageNumber) {
		if ((pageNumber == null) || (pageNumber == 0)) {
			return;
		}

		this.page = pageNumber;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public boolean getFirstPage() {
		return getPage() == 1;
	}

	public boolean getLastPage() {
		return getPage() >= this.pageCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
		this.pageCount = (totalCount / itemsPerPage);
		if ((totalCount % itemsPerPage) > 0) {
			this.pageCount++;
		}
	}

	public long getTotalCount() {
		return totalCount;
	}
}