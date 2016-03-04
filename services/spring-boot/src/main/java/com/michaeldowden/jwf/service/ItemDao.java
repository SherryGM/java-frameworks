package com.michaeldowden.jwf.service;

import static java.util.Collections.emptyMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.michaeldowden.jwf.model.Bourbon;
import com.michaeldowden.jwf.web.ResourceNotFoundException;

@Repository
public class ItemDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<Bourbon> allBourbon() {
		final String sql = "SELECT * FROM store.items";
		return jdbc.query(sql, emptyMap(), new BourbonMapper());
	}

	public Bourbon findBourbon(final Integer itemId) {
		try {
			final String sql = "SELECT * FROM store.items WHERE id=:itemId";
			return jdbc.queryForObject(sql, new MapSqlParameterSource("itemId", itemId),
					new BourbonMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(e.getLocalizedMessage());
		}
	}

	public Bourbon findBourbonByShortname(final String shortname) {
		try {
			final String sql = "SELECT * FROM store.items WHERE shortname=:shortname";
			return jdbc.queryForObject(sql, new MapSqlParameterSource("shortname", shortname),
					new BourbonMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(e.getLocalizedMessage());
		}
	}

	private static final class BourbonMapper implements RowMapper<Bourbon> {

		public Bourbon mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bourbon item = new Bourbon();
			item.setId(rs.getInt("id"));
			item.setShortname(rs.getString("shortname"));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setPrice(rs.getBigDecimal("price"));
			return item;
		}

	}
}
