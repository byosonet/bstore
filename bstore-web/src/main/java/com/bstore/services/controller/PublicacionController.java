package com.bstore.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.PublicacionService;

@Controller
public class PublicacionController {
	private final Logger log = Logger.getLogger(PublicacionController.class);
	
	@Autowired
	private PublicacionService publicacionService;
	
	@RequestMapping(value="/coleccion/{id}",method = RequestMethod.GET)
	   public String novedades(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		log.info("Cargando publicacion.");
		log.info("Cargando Service publicacion:");
		List<Publicacion> lista = this.publicacionService.getPublicacionesByColeccionID(Integer.valueOf(id).intValue());
		log.info("Total publicaciones encontradas: "+lista.size());
		model.addAttribute("publicaciones", lista);
		return "publicaciones";
	   }
	
}
