package com.itacademy.jd2.pk.hop.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.Track;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Customer_;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Route;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Route_;

@Repository
public class RouteDaoImpl extends AbstractDaoImpl<IRoute, Integer> implements IRouteDao {

    protected RouteDaoImpl() {
        super(Route.class);
    }

    @Override
    public IRoute createEntity() {
        Route route = new Route();
        return route;
    }

    @Override
    public List<IRoute> find(RouteFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRoute> cq = cb.createQuery(IRoute.class);
        final Root<Route> from = cq.from(Route.class);
        cq.select(from);
        from.fetch(Route_.customer, JoinType.LEFT);

        final String sortColumn = filter.getSortColumn();
        if (sortColumn != null) {
            final Path<?> expression = getSortPath(from, sortColumn);
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
        }

        if (!filter.getUserRole().equals("ADMIN")) {
            cq.where(cb.equal(from.get(Route_.customer).get(Customer_.id), filter.getCustomerId()));
        }
        final TypedQuery<IRoute> q = em.createQuery(cq);

        setPaging(filter, q);
        return q.getResultList();
    }

    private Path<?> getSortPath(final Root<Route> from, final String sortColumn) {
        switch (sortColumn) {
        case "name":
            return from.get(Route_.name);
        case "created":
            return from.get(Route_.created);
        case "updated":
            return from.get(Route_.updated);
        case "id":
            return from.get(Route_.id);
        case "customer_id":
            return from.get(Route_.customer);
        case "way":
            return from.get(Route_.track);

        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

    @Override
    public long getCount(RouteFilter filter) {

        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<Route> from = cq.from(Route.class);
        cq.select(cb.count(from));

        if (!filter.getUserRole().equals("ADMIN")) {
            cq.where(cb.equal(from.get(Route_.customer).get(Customer_.id), filter.getCustomerId()));
        }
        final TypedQuery<Long> q = em.createQuery(cq);

        return q.getSingleResult();
    }

    @Override
    public IRoute get(Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRoute> cq = cb.createQuery(IRoute.class);
        final Root<Route> from = cq.from(Route.class);
        cq.select(from);
        from.fetch(Route_.customer, JoinType.LEFT);

        cq.where(cb.equal(from.get(Route_.id), id));
        final TypedQuery<IRoute> q = em.createQuery(cq);

        List<IRoute> resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);

    }

    @Override
    public List<IRoute> getCustomerRoutes(Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRoute> cq = cb.createQuery(IRoute.class);
        final Root<Route> from = cq.from(Route.class);
        cq.select(from);
        from.fetch(Route_.customer, JoinType.LEFT);

        cq.where(cb.equal(from.get(Route_.customer).get(Customer_.id), id));
        final TypedQuery<IRoute> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<IRoute> getRoutesByTrack(Track track) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRoute> cq = cb.createQuery(IRoute.class);
        final Root<Route> from = cq.from(Route.class);
        cq.select(from);
        from.fetch(Route_.customer, JoinType.LEFT);

        cq.where(cb.equal(from.get(Route_.track), track));
        final TypedQuery<IRoute> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public void deleteRouteFromMapsList(Integer routeId) {
        final EntityManager em = getEntityManager();
        em.createNativeQuery((String.format("delete from map_2_route e where e.route_id=%s", routeId))).executeUpdate();

    }

}
