package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.INewsDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.INews;
import com.itacademy.jd2.pk.hop.dao.api.filter.NewsFilter;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.News;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class NewsDaoImpl extends AbstractDaoImpl<INews, Integer> implements INewsDao {

	@Override
	public INews createEntity() {

		return new News();
	}

	@Override
	public void update(INews entity) {
		executeStatement(new PreparedStatementAction<INews>(
				String.format("update %s set name=?, info=?, updated=? where id=?", getTableName())) {
			@Override
			public INews doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getInfo());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(INews entity) {
		executeStatement(new PreparedStatementAction<INews>(
				String.format("insert into %s (name, created, updated, info) values(?,?,?,?)", getTableName()), true) {
			@Override
			public INews doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getInfo());

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
		return "news";
	}

	@Override
	protected INews parseRow(ResultSet resultSet) throws SQLException {
		INews entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setInfo(resultSet.getString("info"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<INews> find(NewsFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(NewsFilter filter) {
		return executeCountQuery("");
	}
}