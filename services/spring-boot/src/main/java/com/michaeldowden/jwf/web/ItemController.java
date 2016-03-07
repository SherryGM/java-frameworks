package com.michaeldowden.jwf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaeldowden.jwf.model.Bourbon;
import com.michaeldowden.jwf.service.ItemDao;

@RestController
public class ItemController {

	@Autowired
	ItemDao itemDao;

	@RequestMapping("/svc/items")
	public List<Bourbon> listItems() {
		return itemDao.listItems();
	}

	@RequestMapping("/svc/item/{shortname}")
	public Bourbon fetchItem(@PathVariable("shortname") String shortname) {
		return itemDao.findBourbonByShortname(shortname);
	}

}
