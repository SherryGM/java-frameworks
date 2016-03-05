package com.michaeldowden.jwf.web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import com.codahale.metrics.annotation.Timed;
import com.michaeldowden.jwf.model.Bourbon;
import com.michaeldowden.jwf.service.ItemDao;

@Path("/svc")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
	private DBI dbi;

	public ItemResource(DBI dbi) {
		this.dbi = dbi;
	}

	@GET
	@Timed
	@Path("/items")
	public List<Bourbon> listItems() throws Exception {
		try (ItemDao itemDao = dbi.open(ItemDao.class)) {
			return itemDao.listItems();
		}
	}

}
