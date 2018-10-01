package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.itacademy.jd2.pk.hop.dao.api.IUserDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUser;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.User;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

public class UserDaoImpl extends AbstractDaoImpl<IUser, Integer> implements IUserDao {

	// need override method delete( BY id)
	// need override method deleteAll

	@Override
	public IUser createEntity() {
		return new User();
	}

	@Override
	public void update(IUser entity) {
		executeStatement(new PreparedStatementAction<IUser>(String
				.format("update %s set name=?, surname=?, phone=?, city_id, updated=? where id=?", getTableName())) {
			@Override
			public IUser doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSurname());
				pStmt.setString(3, entity.getPhone());
				pStmt.setInt(4, entity.getCityId());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IUser entity) {
		executeStatement(new PreparedStatementAction<IUser>(
				String.format("insert into %s (name, surname, phone, city_id, created, updated) values(?,?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IUser doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getSurname());
				pStmt.setString(3, entity.getPhone());
				pStmt.setInt(4, entity.getCityId());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
		return "user";
	}

}
