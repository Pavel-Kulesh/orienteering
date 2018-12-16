package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.Track;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;
import com.itacademy.jd2.pk.hop.service.IRouteService;

@Service
public class RouteServiceImpl implements IRouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    private IPointDao pointDao;

    private IRouteDao dao;

    @Autowired
    public RouteServiceImpl(IPointDao pointDao, IRouteDao dao) {
        super();
        this.pointDao = pointDao;
        this.dao = dao;
    }

    @Override
    public IRoute get(Integer id) {

        IRoute entity = dao.get(id);
        return entity;
    }

    @Override
    public List<IRoute> getAll() {
        List<IRoute> all = dao.selectAll();
        return all;
    }

    @Override
    public void save(IRoute entity) {
        // after insert route need parse file => get list point_route ==>>
        // ==>> insertList(point list)

        Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new route created: {}", entity);
            // ===>>>
        } else {
            dao.update(entity);
            LOGGER.debug("route updated: {}", entity);

        }
    }

    @Override
    public void delete(Integer id) {
        pointDao.delete(id);

        dao.deleteRouteFromMapsList(id);
        dao.delete(id);
        LOGGER.info("delete route with id=" + id + " +delete all point where route_id=id");

    }

    @Override
    public void deleteAll() {
        pointDao.deleteAll();
        dao.deleteAll();
        LOGGER.info("delete all routes and point");

    }

    @Override
    public IRoute createEntity() {
        return dao.createEntity();
    }

    @Override
    public List<IRoute> find(RouteFilter filter) {

        return dao.find(filter);
    }

    @Override
    public long getCount(RouteFilter filter) {

        return dao.getCount(filter);
    }

    @Override
    public List<IRoute> getCustomerRoutes(Integer id) {

        return dao.getCustomerRoutes(id);
    }

    @Override
    public List<IRoute> getRoutesByTrack(Track track) {
        return dao.getRoutesByTrack(track);
    }

    @Override
    public void deleteRouteFromMapsList(Integer routeId) {
        dao.deleteRouteFromMapsList(routeId);

    }

}
