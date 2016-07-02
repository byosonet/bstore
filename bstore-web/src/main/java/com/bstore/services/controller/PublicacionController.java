package com.bstore.services.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
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
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		 model.addAttribute("menu",menu);
		log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
		
		List<Publicacion> lista = this.publicacionService.getPublicacionesByColeccionID(Integer.valueOf(id).intValue());
		log.info("Total publicaciones encontradas: "+lista.size());
		
		model.addAttribute("publicaciones", lista);
		return "publicaciones";
	   }
	
	@RequestMapping(value="/publicacion/{id}",method = RequestMethod.GET)
	   public String getPublicacionHTML(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		log.info("Cargando publicacion.");
		log.info("Cargando Service publicacion:");
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		 model.addAttribute("menu",menu);
		log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
		
		Publicacion pub = this.publicacionService.getPublicacion(Integer.valueOf(id).intValue());
		log.info("URL Encontrada: "+pub.getUrlArchivo());
		model.addAttribute("urlPublicacion", pub.getUrlArchivo());
		model.addAttribute("nombrePublicacion", pub.getNombre());
		
		return "publicacionHTML";
	   }
	
}
