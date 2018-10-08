package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Point;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PointDaoImpl extends AbstractDaoImpl<IPoint, Integer> implements IPointDao {

	@Override
	public IPoint createEntity() {
		return new Point();
	}

	@Override
	public void update(IPoint entity) {
		// can update anything?

		executeStatement(new PreparedStatementAction<IPoint>(
				String.format("update %s set updated=? where id=?", getTableName())) {
			@Override
			public IPoint doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IPoint entity) {
		executeStatement(new PreparedStatementAction<IPoint>(String.format(
				"insert into %s (created, updated, route_id, latitude, longitude, diff_time) values(?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IPoint doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setObject(1, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getRouteId());
				pStmt.setDouble(4, entity.getLatitude());
				pStmt.setDouble(5, entity.getLongitude());
				pStmt.setInt(6, entity.getDiffTime());

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
		return "point";
	}

	@Override
	protected IPoint parseRow(ResultSet resultSet) throws SQLException {
		IPoint entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		Integer routeId = (Integer) resultSet.getObject("route_id");
		entity.setRouteId(routeId);

		Double latitude = (Double) resultSet.getObject("latitude");
		entity.setLatitude(latitude);
		Double longitude = (Double) resultSet.getObject("longitude");
		entity.setLongitude(longitude);

		Integer diffTime = (Integer) resultSet.getObject("diff_time");
		entity.setDiffTime(diffTime);

		return entity;
	}

	@Override
	public void delete(Integer id) {
		// this method delete all point where route_id=id;

		executeStatement(
				new PreparedStatementAction<Integer>(String.format("delete from %s where route_id=?", getTableName())) {
					@Override
					public Integer doWithPreparedStatement(final PreparedStatement prepareStatement)
							throws SQLException {
						prepareStatement.setObject(1, id);
						return prepareStatement.executeUpdate();
					}
				});
	}

}
