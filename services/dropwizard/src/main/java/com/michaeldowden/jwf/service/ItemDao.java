package com.michaeldowden.jwf.service;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.michaeldowden.jwf.model.Bourbon;

@RegisterMapper(BourbonMapper.class)
public interface ItemDao extends AutoCloseable {
	@SqlQuery("SELECT * FROM store.items")
	public List<Bourbon> listItems();

	@SqlQuery("SELECT * FROM store.items WHERE id = :itemId")
	public Bourbon findBourbon(@Bind("itemId") Integer itemId);

	@SqlQuery("SELECT * FROM store.items WHERE shortname = :shortname")
	public Bourbon findBourbonByShortname(@Bind("shortname") String shortname);
}
