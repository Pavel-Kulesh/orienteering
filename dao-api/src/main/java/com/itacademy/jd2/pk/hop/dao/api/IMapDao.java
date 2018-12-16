package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;

public interface IMapDao extends IDao<IMap, Integer> {
    List<IMap> find(MapFilter filter);

    long getCount(MapFilter filter);

    IMap getFullInfo(Integer mapId);

}
