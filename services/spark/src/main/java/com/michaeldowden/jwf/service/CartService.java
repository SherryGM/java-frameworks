package com.michaeldowden.jwf.service;

import static java.math.BigDecimal.valueOf;
import static spark.Spark.halt;

import java.math.BigDecimal;

import spark.Request;

import com.michaeldowden.jwf.model.OrderItem;
import com.michaeldowden.jwf.model.ShoppingCart;

public class CartService {
	private static final CartService SINGLETON = new CartService();
	private static final String SHOPPING_CART = "SHOPPING_CART";

	private CartService() {
	}

	public static CartService getInstance() {
		return SINGLETON;
	}

	private void calculateTotal(ShoppingCart shoppingCart) {
		BigDecimal total = BigDecimal.valueOf(0.0);
		for (OrderItem item : shoppingCart.getItems()) {
			BigDecimal subtotal = item.getPrice().multiply(valueOf(item.getQty()));
			total = total.add(subtotal);
		}
		shoppingCart.setTotal(total);
	}

	private int findItemInCart(ShoppingCart shoppingCart, Integer itemId) {
		for (int i = 0; i < shoppingCart.getItems().size(); i++) {
			OrderItem item = shoppingCart.getItems().get(i);
			if (item.getId() == itemId) {
				return i;
			}
		}
		return -1;
	}

	public ShoppingCart fetchCart(Request req) {
		Object cart = req.session().attribute(SHOPPING_CART);
		if (cart == null) {
			cart = new ShoppingCart();
			req.session().attribute(SHOPPING_CART, cart);
		}
		calculateTotal((ShoppingCart) cart);
		return (ShoppingCart) cart;
	}

	public void addToCart(Request req, OrderItem newItem) {
		ShoppingCart shoppingCart = fetchCart(req);
		int i = findItemInCart(shoppingCart, newItem.getId());
		if (i < 0) {
			// Add a new item to the cart
			shoppingCart.getItems().add(newItem);
		} else {
			// Update quantity for existing item
			OrderItem cartItem = shoppingCart.getItems().get(i);
			cartItem.setQty(cartItem.getQty() + newItem.getQty());
		}
	}

	public void updateQuantity(Request req, Integer itemId, Integer qty) {
		ShoppingCart shoppingCart = fetchCart(req);
		int i = findItemInCart(shoppingCart, itemId);
		if (i < 0) {
			halt(404, "Item doesn't exist in Shopping Cart");
		} else {
			OrderItem cartItem = shoppingCart.getItems().get(i);
			cartItem.setQty(qty);
		}
	}

	public void removeFromCart(Request req, Integer itemId) {
		ShoppingCart shoppingCart = fetchCart(req);
		int i = findItemInCart(shoppingCart, itemId);
		if (i < 0) {
			halt(404, "Item doesn't exist in Shopping Cart");
		} else {
			shoppingCart.getItems().remove(i);
		}
	}
}
