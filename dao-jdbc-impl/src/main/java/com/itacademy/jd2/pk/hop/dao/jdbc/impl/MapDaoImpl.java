package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IMapDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Customer;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Map;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class MapDaoImpl extends AbstractDaoImpl<IMap, Integer> implements IMapDao {

	@Override
	public IMap createEntity() {
		return new Map();
	}

	@Override
	public void update(IMap entity) {
		executeStatement(new PreparedStatementAction<IMap>(String.format(
				"update %s set name=?, latitude1=?, longitude1=?, latitude2=?, longitude2=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IMap doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());

				pStmt.setObject(2, entity.getLatitude1());
				pStmt.setObject(3, entity.getLongitude1());
				pStmt.setObject(4, entity.getLatitude2());
				pStmt.setObject(5, entity.getLatitude2());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});

	}

	@Override
	public void insert(IMap entity) {
		executeStatement(new PreparedStatementAction<IMap>(String.format(
				"insert into %s (name, customer_id, latitude1, longitude1, latitude2, longitude2, created, updated) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IMap doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCustomer().getId());
				pStmt.setObject(3, entity.getLatitude1());
				pStmt.setObject(4, entity.getLongitude1());
				pStmt.setObject(5, entity.getLatitude2());
				pStmt.setObject(6, entity.getLatitude2());
				pStmt.setObject(7, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);

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
		return "map";
	}

	@Override
	protected IMap parseRow(ResultSet resultSet) throws SQLException {
		IMap entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));

		Customer customer = new Customer();
		Integer creatorId = (Integer) resultSet.getObject("customer_id");
		customer.setId(creatorId);
		entity.setCustomer(customer);

		entity.setLatitude1((BigDecimal) resultSet.getObject("latitude1"));
		entity.setLongitude1((BigDecimal) resultSet.getObject("longitude1"));
		entity.setLatitude2((BigDecimal) resultSet.getObject("latitude2"));
		entity.setLongitude2((BigDecimal) resultSet.getObject("longitude2"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public List<IMap> find(MapFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(MapFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public void addRouteToEvent(Integer mapId, Integer routeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRouteFromEvent(Integer mapId, Integer routeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IRoute> getRoutesOnMap(Integer mapId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

}
