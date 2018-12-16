package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.Track;
import com.itacademy.jd2.pk.hop.dao.api.filter.RouteFilter;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Customer;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Route;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class RouteDaoImpl extends AbstractDaoImpl<IRoute, Integer> implements IRouteDao {

    @Override
    public IRoute createEntity() {
        return new Route();
    }

    @Override
    public void update(IRoute entity) {
        // can we update userId? ex: org use gps-track => them put its customer
        // ===>>> not update user_id

        executeStatement(new PreparedStatementAction<IRoute>(
                String.format("update %s set name=?, updated=? where id=?", getTableName())) {
            @Override
            public IRoute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
                pStmt.setString(1, entity.getName());
                pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
                pStmt.setInt(3, entity.getId());

                pStmt.executeUpdate();
                return entity;
            }
        });
    }

    @Override
    public void insert(IRoute entity) {
        executeStatement(new PreparedStatementAction<IRoute>(
                String.format("insert into %s (name, customer_id, created, updated) values(?,?,?,?)", getTableName()),
                true) {
            @Override
            public IRoute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
                pStmt.setString(1, entity.getName());
                pStmt.setInt(2, entity.getCustomer().getId());
                pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
                pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

                pStmt.executeUpdate();

                final ResultSet rs = pStmt.getGeneratedKeys();
                rs.next();
                final int id = rs.getInt("id");

                rs.close();

                entity.setId(id);
                return entity;
            }
        });
    }

    @Override
    protected String getTableName() {
        return "route";
    }

    @Override
    protected IRoute parseRow(ResultSet resultSet) throws SQLException {
        IRoute entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setName(resultSet.getString("name"));
        entity.setCreated(resultSet.getTimestamp("created"));
        entity.setUpdated(resultSet.getTimestamp("updated"));

        Customer customer = new Customer();
        Integer userId = (Integer) resultSet.getObject("customer_id");
        customer.setId(userId);
        entity.setCustomer(customer);

        return entity;
    }

    @Override
    public List<IRoute> find(RouteFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getCount(RouteFilter filter) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<IRoute> getCustomerRoutes(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IRoute> getRoutesByTrack(Track track) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteRouteFromMapsList(Integer routeId) {
        // TODO Auto-generated method stub

    }

}
