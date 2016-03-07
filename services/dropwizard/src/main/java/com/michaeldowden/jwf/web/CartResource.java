package com.michaeldowden.jwf.web;

import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import com.michaeldowden.jwf.model.OrderItem;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.service.CartService;
import com.michaeldowden.jwf.service.ItemDao;
import com.michaeldowden.jwf.utils.ItemNotFoundException;

@Path("/svc")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
	private CartService cartSvc = new CartService();
	private DBI dbi;

	public CartResource(DBI dbi) {
		this.dbi = dbi;
	}

	@GET
	@Path("/cart")
	public ShoppingCart fetchCart(@Session HttpSession session) throws Exception {
		return cartSvc.fetchCart(session);
	}

	@PUT
	@Path("/cart/{itemId}")
	public Response addToCart(@PathParam("itemId") IntParam itemId, @Session HttpSession session) {
		try (ItemDao itemDao = dbi.open(ItemDao.class)) {
			OrderItem item = new OrderItem(itemDao.findBourbon(itemId.get()));

			cartSvc.addToCart(session, item);
			return Response.ok(item).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/cart/{itemId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateQuantity(@PathParam("itemId") IntParam itemId,
			@FormParam("qty") IntParam qty, @Session HttpSession session) {
		try {
			cartSvc.updateQuantity(session, itemId.get(), qty.get());
		} catch (ItemNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(itemId.get()).build();
	}

	@DELETE
	@Path("/cart/{itemId}")
	public Response removeFromCart(@PathParam("itemId") IntParam itemId,
			@Session HttpSession session) {
		try {
			cartSvc.removeFromCart(session, itemId.get());
		} catch (ItemNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(itemId.get()).build();
	}

}
