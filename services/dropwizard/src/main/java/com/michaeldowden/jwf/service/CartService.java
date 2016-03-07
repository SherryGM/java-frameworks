package com.michaeldowden.jwf.service;

import javax.servlet.http.HttpSession;

import com.michaeldowden.jwf.model.OrderItem;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.utils.CartUtils;
import com.michaeldowden.jwf.utils.ItemNotFoundException;

public class CartService {
	private static final String SHOPPING_CART = "SHOPPING_CART";

	public ShoppingCart fetchCart(HttpSession session) {
		Object cart = session.getAttribute(SHOPPING_CART);
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute(SHOPPING_CART, cart);
		}
		return (ShoppingCart) cart;
	}

	public void addToCart(HttpSession session, OrderItem newItem) {
		CartUtils.addToCart(fetchCart(session), newItem);
	}

	public void updateQuantity(HttpSession session, Integer itemId, Integer qty)
			throws ItemNotFoundException {
		CartUtils.updateQuantity(fetchCart(session), itemId, qty);
	}

	public void removeFromCart(HttpSession session, Integer itemId) throws ItemNotFoundException {
		CartUtils.removeFromCart(fetchCart(session), itemId);
	}
}
