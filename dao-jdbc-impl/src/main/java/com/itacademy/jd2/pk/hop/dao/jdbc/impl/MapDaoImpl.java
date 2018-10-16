package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IMapDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
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
				"update %s set name=?, path=?, file=?, latitude1=?, longitude1=?, latitude2=?, longitude2=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IMap doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getPath());
				pStmt.setString(3, entity.getFile());
				pStmt.setObject(4, entity.getLatitude1());
				pStmt.setObject(5, entity.getLongitude1());
				pStmt.setObject(6, entity.getLatitude2());
				pStmt.setObject(7, entity.getLatitude2());
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(9, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});

	}

	@Override
	public void insert(IMap entity) {
		executeStatement(new PreparedStatementAction<IMap>(String.format(
				"insert into %s (name, user_id, path, file, latitude1, longitude1, latitude2, longitude2, created, updated) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IMap doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {

				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getUserId());
				pStmt.setString(3, entity.getPath());
				pStmt.setString(4, entity.getFile());
				pStmt.setObject(5, entity.getLatitude1());
				pStmt.setObject(6, entity.getLongitude1());
				pStmt.setObject(7, entity.getLatitude2());
				pStmt.setObject(8, entity.getLatitude2());
				pStmt.setObject(9, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(10, entity.getUpdated(), Types.TIMESTAMP);

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
		entity.setUserId((Integer) resultSet.getObject("user_id"));
		entity.setPath(resultSet.getString("path"));
		entity.setFile(resultSet.getString("file"));
		entity.setLatitude1((BigDecimal)resultSet.getObject("latitude1"));
		entity.setLongitude1((BigDecimal)resultSet.getObject("longitude1"));
		entity.setLatitude2((BigDecimal)resultSet.getObject("latitude2"));
		entity.setLongitude2((BigDecimal)resultSet.getObject("longitude2"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

}
