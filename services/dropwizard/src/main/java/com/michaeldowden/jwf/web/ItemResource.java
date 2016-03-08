package com.michaeldowden.jwf.web;

import io.dropwizard.jersey.params.NonEmptyStringParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.michaeldowden.jwf.model.Bourbon;
import com.michaeldowden.jwf.service.ItemDao;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
	private DBI dbi;

	public ItemResource(DBI dbi) {
		this.dbi = dbi;
	}

	@GET
	@Path("/items")
	public List<Bourbon> listItems() throws Exception {
		try (ItemDao itemDao = dbi.open(ItemDao.class)) {
			return itemDao.listItems();
		}
	}

	@GET
	@Path("/item/{shortname}")
	public Response fetchItem(@PathParam("shortname") NonEmptyStringParam shortname)
			throws Exception {
		try (ItemDao itemDao = dbi.open(ItemDao.class)) {
			Bourbon item = itemDao.findBourbonByShortname(shortname.get().get());
			return item == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(
					item).build();
		}
	}
}
