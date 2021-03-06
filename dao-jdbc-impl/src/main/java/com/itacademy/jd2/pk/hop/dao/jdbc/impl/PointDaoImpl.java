package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IPointDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Point;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Route;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class PointDaoImpl extends AbstractDaoImpl<IPoint, Integer> implements IPointDao {

	@Override
	public IPoint createEntity() {
		return new Point();
	}

	@Override
	public void update(IPoint entity) {
		throw new RuntimeException("not supported");
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
				pStmt.setInt(3, entity.getRoute().getId());
				pStmt.setDouble(4, entity.getLatitude());
				pStmt.setDouble(5, entity.getLongitude());
				pStmt.setObject(6, entity.getDiffTime());

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

		Route route = new Route();
		Integer routeId = (Integer) resultSet.getObject("route_id");
		route.setId(routeId);
		entity.setRoute(route);
		;

		Double latitude = resultSet.getDouble("latitude");
		entity.setLatitude(latitude);

		Double longitude = resultSet.getDouble("longitude");
		entity.setLongitude(longitude);

		Integer diffTime = (Integer) resultSet.getObject("diff_time");
		entity.setDiffTime(diffTime);

		return entity;
	}

	@Override
	public void delete(Integer id) {
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

	@Override
	public void insertList(List<IPoint> entities) {

		try (Connection c = getConnection()) {
			c.setAutoCommit(false);
			try {

				for (IPoint entity : entities) {
					PreparedStatement pStmt = c.prepareStatement(String.format(
							"insert into %s (created, updated, route_id, latitude, longitude, diff_time) values(?,?,?,?,?,?)",
							getTableName()), Statement.RETURN_GENERATED_KEYS);

					pStmt.setObject(1, entity.getCreated(), Types.TIMESTAMP);
					pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
					pStmt.setInt(3, entity.getRoute().getId());
					pStmt.setDouble(4, entity.getLatitude());
					pStmt.setDouble(5, entity.getLongitude());
					pStmt.setObject(6, entity.getDiffTime());

					pStmt.executeUpdate();

					final ResultSet rs = pStmt.getGeneratedKeys();
					rs.next();
					final int id = rs.getInt("id");

					rs.close();
					pStmt.close();

					entity.setId(id);
				}

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public List<IPoint> selectById(Integer id) {
		throw new RuntimeException("not implemented");
	}

}
