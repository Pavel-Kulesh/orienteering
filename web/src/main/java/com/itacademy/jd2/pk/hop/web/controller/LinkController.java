package com.itacademy.jd2.pk.hop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/links")
public class LinkController {

	@RequestMapping(method = RequestMethod.GET)
	public String getLinks() {
		return "links";
	}

}
