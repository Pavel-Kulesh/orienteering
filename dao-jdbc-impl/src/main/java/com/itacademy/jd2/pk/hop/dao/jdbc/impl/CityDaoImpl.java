package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICityDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICity;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.City;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Country;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<ICity, Integer> implements ICityDao {

	@Override
	public ICity createEntity() {

		return new City();
	}

	@Override
	public void update(ICity entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set name=?, updated=?, country_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getCountry().getId());
				pStmt.setInt(4, entity.getId());
				pStmt.executeUpdate();
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
	public void insert(ICity entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (name, created, updated, country_id) values(?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getCountry().getId());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);

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
	protected String getTableName() {

		return "city";
	}

	@Override
	protected ICity parseRow(ResultSet resultSet, Set<String> columns) throws SQLException {
		ICity entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		Country country = new Country();
		Integer countryId = (Integer) resultSet.getObject("country_id");
		country.setId(countryId);
		entity.setCountry(country);

		return entity;
	}

	@Override
	public List<ICity> getByCountry(Integer id) {
		 throw new RuntimeException("not implemented");
	}

}
