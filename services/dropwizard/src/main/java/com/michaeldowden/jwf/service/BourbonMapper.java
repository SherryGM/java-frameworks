package com.michaeldowden.jwf.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.michaeldowden.jwf.model.Bourbon;

public class BourbonMapper implements ResultSetMapper<Bourbon> {

	@Override
	public Bourbon map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		Bourbon item = new Bourbon();
		item.setId(rs.getInt("id"));
		item.setShortname(rs.getString("shortname"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getBigDecimal("price"));
		return item;
	}

}
