package com.itacademy.jd2.pk.hop.dao.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.pk.hop.dao.api.IEventDao;
import com.itacademy.jd2.pk.hop.dao.api.entity.IEvent;
import com.itacademy.jd2.pk.hop.dao.api.entity.Type;
import com.itacademy.jd2.pk.hop.dao.api.filter.EventFilter;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.entity.Event;
import com.itacademy.jd2.pk.hop.dao.jdbc.impl.util.PreparedStatementAction;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	@Override
	public IEvent createEntity() {

		return new Event();
	}

	@Override
	public void update(IEvent entity) {
		executeStatement(new PreparedStatementAction<IEvent>(String.format(
				"update %s set name=?, updated=?, date=?, country_id=?, type=?, info=?, latitude=?, longitude=? where id=?",
				getTableName())) {
			@Override
			public IEvent doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getDate(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getCountryId());
				pStmt.setString(5, entity.getType().name());
				pStmt.setString(6, entity.getInfo());
				pStmt.setDouble(7, entity.getLatitude());
				pStmt.setDouble(8, entity.getLongitude());
				pStmt.setInt(9, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});

	}

	@Override
	public void insert(IEvent entity) {
		executeStatement(new PreparedStatementAction<IEvent>(String.format(
				"insert into %s (name, created, updated, creator_id, country_id, date, type, info, latitude, longitude) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IEvent doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getCreatorId());
				pStmt.setInt(5, entity.getCountryId());
				pStmt.setObject(6, entity.getDate(), Types.TIMESTAMP);
				pStmt.setString(7, entity.getType().name());
				pStmt.setString(8, entity.getInfo());
				pStmt.setDouble(9, entity.getLatitude());
				pStmt.setDouble(10, entity.getLongitude());

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
		return "event";
	}

	@Override
	protected IEvent parseRow(ResultSet resultSet) throws SQLException {
		IEvent entity = createEntity();

		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		entity.setName(resultSet.getString("name"));

		Integer creatorId = (Integer) resultSet.getObject("creator_id");
		entity.setCreatorId(creatorId);

		entity.setDate(resultSet.getTimestamp("date"));

		Integer countryId = (Integer) resultSet.getObject("country_id");
		entity.setCountryId(countryId);

		entity.setType(Type.valueOf(resultSet.getString("type")));

		entity.setInfo(resultSet.getString("info"));

		Double latitude = resultSet.getDouble("latitude");
		entity.setLatitude(latitude);
		Double longitude = resultSet.getDouble("longitude");
		entity.setLongitude(longitude);

		return entity;
	}

	@Override
	protected IEvent parseRow(ResultSet resultSet, Set<String> columns) throws SQLException {
		// need this method?
		return super.parseRow(resultSet, columns);
	}

	@Override
	public void delete(Integer id) {

		// need delete another object:item_event;
		executeStatement(
				new PreparedStatementAction<Integer>(String.format("delete from %s where id=?", getTableName())) {
					@Override
					public Integer doWithPreparedStatement(final PreparedStatement prepareStatement)
							throws SQLException {
						prepareStatement.setObject(1, id);
						return prepareStatement.executeUpdate();
					}
				});
	}

	@Override
	public void deleteAll() {
		// need delete all item_event;
		executeStatement(new PreparedStatementAction<Integer>("delete from " + getTableName()) {
			@Override
			public Integer doWithPreparedStatement(final PreparedStatement prepareStatement) throws SQLException {
				final int executeUpdate = prepareStatement.executeUpdate();
				return executeUpdate;
			}
		});
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(EventFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public List<IEvent> getEventsByCustomer(Integer id) {
		String text = String
				.format("select * from event a1 join user_2_event b1 on a1.id=b1.event_id where user_id=%s;", id);
		return executeFindQueryWithCustomSelect(text);
	}

}
