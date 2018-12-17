package com.itacademy.jd2.pk.hop.dao.api;

import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.TypeTrack;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;

public interface IRouteDao extends IDao<IRoute, Integer> {
    List<IRoute> find(RouteFilter filter);

    long getCount(RouteFilter filter);

    List<IRoute> getCustomerRoutes(Integer id);

    void deleteRouteFromMapsList(Integer routeId);

    List<IRoute> getRoutesByTrack(TypeTrack track);

}
