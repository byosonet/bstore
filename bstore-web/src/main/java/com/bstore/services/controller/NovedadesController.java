package com.bstore.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NovedadesController {
	private final Logger log = Logger.getLogger(NovedadesController.class);
	
	@RequestMapping(value="/novedades",method = RequestMethod.GET)
	   public String novedades(Model model, HttpServletRequest request) {
		log.info("Cargando novedades.");
	      
		return "novedades";
	   }
	
}
