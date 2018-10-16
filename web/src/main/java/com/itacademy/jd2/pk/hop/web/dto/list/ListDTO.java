package com.itacademy.jd2.pk.hop.web.dto.list;

import java.util.List;

public class ListDTO<T> {

    public static final String LIST_MODEL_ATTRIBUTE = "listDTO";

    private List<T> list;

    private SortDTO sort;

    public ListDTO() {
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
}
