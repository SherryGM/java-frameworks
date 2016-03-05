package com.michaeldowden.jwf;

import static spark.Spark.get;
import static spark.Spark.port;

import com.michaeldowden.jwf.web.CartController;
import com.michaeldowden.jwf.web.ItemController;

public class BourbonStore {

	public static void main(String[] args) throws Exception {
		port(8080); // Match traditional JEE port

		get("/hello", (req, res) -> "Hello World");

		new ItemController().initialize();
		new CartController().initialize();
	}

}
