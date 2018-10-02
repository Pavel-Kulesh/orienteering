package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICountryDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICountry;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Country;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {

	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public void update(ICountry entity) {
		executeStatement(new PreparedStatementAction<ICountry>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public ICountry doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(ICountry entity) {
		executeStatement(new PreparedStatementAction<ICountry>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public ICountry doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
		return "country";
	}

	@Override
	protected ICountry parseRow(ResultSet resultSet) throws SQLException {
		final ICountry entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}
}
