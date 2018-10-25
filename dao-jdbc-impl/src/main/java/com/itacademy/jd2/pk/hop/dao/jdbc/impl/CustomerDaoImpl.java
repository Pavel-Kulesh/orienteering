package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.ICustomerDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.ICustomer;
import com.itacademy.jd2.pk.hop.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Customer;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	// need override method delete( BY id)
	// need override method deleteAll

	@Override
	public ICustomer createEntity() {
		return new Customer();
	}

	@Override
	public void update(ICustomer entity) {
		executeStatement(new PreparedStatementAction<ICustomer>(String
				.format("update %s set name=?, surname=?, phone=?, city_id=?, updated=? where id=?", getTableName())) {
			@Override
			public ICustomer doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSurname());
				pStmt.setObject(3, entity.getPhone());
				pStmt.setInt(4, entity.getCityId());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(ICustomer entity) {
		executeStatement(new PreparedStatementAction<ICustomer>(String.format(
				"insert into %s (id, name, surname, phone, city_id, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICustomer doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getId());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getSurname());
				pStmt.setObject(4, entity.getPhone());
				pStmt.setInt(5, entity.getCityId());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				/*
				 * final ResultSet rs = pStmt.getGeneratedKeys(); rs.next(); final int id =
				 * rs.getInt("id");
				 * 
				 * rs.close();
				 * 
				 * 
				 * entity.setId(id);
				 */
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "customer";
	}

	@Override
	protected ICustomer parseRow(ResultSet resultSet) throws SQLException {
		ICustomer entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setSurname(resultSet.getString("surname"));
		entity.setPhone((String) resultSet.getObject("phone"));
		entity.setCityId((Integer) resultSet.getObject("city_id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(CustomerFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public List<ICustomer> showParticipant(Integer id) {
		String text = String
				.format("select * from customer a1 join item_event b1 on a1.id=b1.user_id where event_id=%s;", id);
		return executeFindQueryWithCustomSelect(text);
	}

}
