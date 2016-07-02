package com.bstore.services.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.ColeccionService;

@Controller
public class ColeccionController {
	private final Logger log = Logger.getLogger(ColeccionController.class);
	
	@Autowired
	private ColeccionService coleccionService;
	
	@RequestMapping(value="/colecciones",method = RequestMethod.GET)
	   public String novedades(Model model, HttpServletRequest request) {
		log.info("Cargando novedades.");
		log.info("Cargando Service Coleccion:");
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		 model.addAttribute("menu",menu);
		log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
		
		List<Coleccion> lista = this.coleccionService.getColeccionDao(true);
		log.info("Total colecciones encontradas: "+lista.size());
		model.addAttribute("colecciones", lista);

		return "colecciones";
	   }
	
}
