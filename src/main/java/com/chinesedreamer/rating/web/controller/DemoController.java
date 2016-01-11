package com.chinesedreamer.rating.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "demo")
public class DemoController {
	
	@RequestMapping(value = "handsontable", method = RequestMethod.GET)
	public String handsontable(HttpServletRequest request, Model model){
		return "demo/handsontable";
	}
}
