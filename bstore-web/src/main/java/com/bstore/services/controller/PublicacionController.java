package com.bstore.services.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.PublicacionService;

@Controller
public class PublicacionController {
	private final Logger log = Logger.getLogger(PublicacionController.class);
	private static final String NAME_CONTROLLER="[--PublicacionController--]";
	
	@Autowired
	private PublicacionService publicacionService;
	
	@RequestMapping(value="/coleccion/{id}",method = RequestMethod.GET)
	   public String coleccionesById(Model model, @PathVariable("id") String id, HttpServletRequest request, 
			   HttpServletResponse response) throws IOException{
		log.info("Cargando publicacion: "+NAME_CONTROLLER+"/coleccion/{id}");
		log.info("Cargando Service publicacion: "+NAME_CONTROLLER+"/coleccion/{id}");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			 model.addAttribute("menu",menu);
			log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
			
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			List<Publicacion> lista = this.publicacionService.getPublicacionesByColeccionID(Integer.valueOf(id).intValue(), usuario.getId());
			log.info("Total publicaciones encontradas: "+lista.size());
			
			model.addAttribute("publicaciones", lista);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "publicaciones";
	   }
	
	@RequestMapping(value="/publicacion/{id}",method = RequestMethod.GET)
	   public String getPublicacionHTML(Model model, @PathVariable("id") String id, HttpServletRequest request, 
			   HttpServletResponse response) throws IOException{
		log.info("Cargando publicacion: "+NAME_CONTROLLER+"/publicacion/{id}");
		log.info("Cargando Service publicacion: "+NAME_CONTROLLER+"/publicacion/{id}");
		
		HttpSession session= (HttpSession) request.getSession(false);
			if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			 model.addAttribute("menu",menu);
			log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
			
			Publicacion pub = this.publicacionService.getPublicacion(Integer.valueOf(id).intValue());
			log.info("URL Encontrada: "+pub.getUrlArchivo());
			model.addAttribute("urlPublicacion", pub.getUrlArchivo());
			model.addAttribute("nombrePublicacion", pub.getNombre());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		
		return "publicacionHTML";
	   }
	
}
