package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IUserAccountDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IUserAccount;
import com.itacademy.jd2.pk.hop.dao.api.entity.Role;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {
	// need override method delete( BY id)
	// need override method deleteAll

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(
				String.format("update %s set email=?, password=?, updated=?, role=? where id=?", getTableName())) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getEmail());
				pStmt.setString(2, entity.getPassword());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getRole().name());
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (email, created, updated, password, role) values(?,?,?,?,?)", getTableName()), true) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getEmail());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getPassword());
				pStmt.setString(5, entity.getRole().name());

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
		return "user_account";
	}

	@Override
	protected IUserAccount parseRow(ResultSet resultSet) throws SQLException {
		IUserAccount entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		entity.setEmail(resultSet.getString("email"));

		// check password only or entity.setPassword;
		entity.setPassword(resultSet.getString("password"));

		entity.setRole(Role.valueOf(resultSet.getString("role")));

		/*
		 * String role = resultSet.getString("role"); Role[] listRole = Role.values();
		 * 
		 * for (Role ob : listRole) { if (role.equals(ob.name())) { entity.setRole(ob);
		 * } }
		 */
		return entity;
	}

}
