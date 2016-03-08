package com.michaeldowden.jwf.web;

import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.service.CartService;
import com.michaeldowden.jwf.service.OrderService;

@Path("/order/")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
	private OrderService orderSvc = new OrderService();
	private CartService cartSvc = new CartService();

	@GET
	@Path("/shipping")
	public Address fetchShippingAddress(@Session HttpSession session) {
		return orderSvc.fetchShippingAddress(session);
	}

	@PUT
	@Path("/shipping")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateShippingAddress(@Session HttpSession session, Address address) {
		orderSvc.updateShippingAddress(session, address);
		return Response.ok().build();
	}

	@POST
	@Path("/checkout")
	public Integer checkout(@Session HttpSession session) {
		return orderSvc.checkout(session, cartSvc.fetchCart(session));
	}

	@GET
	@Path("/{orderNumber}")
	public Order lookupOrder(@PathParam("orderNumber") IntParam orderNumber) {
		return orderSvc.lookupOrder(orderNumber.get());
	}

}
