package com.michaeldowden.jwf.web;

import static spark.Spark.get;
import static spark.Spark.halt;

import com.michaeldowden.jwf.model.Bourbon;
import com.michaeldowden.jwf.service.ItemDao;
import com.michaeldowden.jwf.service.JsonTransformer;

public class ItemController {

	private ItemDao itemDao;

	public ItemController() {
		itemDao = new ItemDao();
	}

	public void initialize() {
		get("/svc/items", "application/json", (req, res) -> {
			return itemDao.listItems();
		}, new JsonTransformer());

		get("/svc/item/:shortname", "application/json", (req, res) -> {
			Bourbon item = itemDao.findBourbonByShortname(req.params(":shortname"));
			if (item == null) {
				halt(404);
			}
			return item;
		}, new JsonTransformer());
	}
}
