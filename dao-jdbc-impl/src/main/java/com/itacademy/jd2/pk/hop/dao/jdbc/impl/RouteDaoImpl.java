package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.itacademy.jd2.pk.hop.dao.api.IRouteDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Route;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

public class RouteDaoImpl extends AbstractDaoImpl<IRoute, Integer> implements IRouteDao {

	@Override
	public IRoute createEntity() {
		return new Route();
	}

	@Override
	public void update(IRoute entity) {
		// can we update userId?

		executeStatement(new PreparedStatementAction<IRoute>(
				String.format("update %s set name=?, path=?, updated=?, userId=? where id=?", getTableName())) {
			@Override
			public IRoute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getPath());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getUserId());
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IRoute entity) {
		executeStatement(new PreparedStatementAction<IRoute>(String.format(
				"insert into %s (name, path, userId, created, updated) values(?,?,?,?,?)", getTableName()), true) {
			@Override
			public IRoute doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getPath());
				pStmt.setInt(3, entity.getUserId());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

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
		entity.setPath(resultSet.getString("path"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		Integer userId = (Integer) resultSet.getObject("user_id");
		entity.setUserId(userId);

		return entity;
	}

}
