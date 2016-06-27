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

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.PublicacionService;

@Controller
public class PublicacionController {
	private final Logger log = Logger.getLogger(PublicacionController.class);
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(value="/coleccion/{id}",method = RequestMethod.GET)
	   public String novedades(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		log.info("Cargando publicacion.");
		log.info("Cargando Service publicacion:");
		List<Publicacion> lista = this.publicacionService.getPublicacionesByColeccionID(Integer.valueOf(id).intValue());
		log.info("Total publicaciones encontradas: "+lista.size());
		
		List<Compra> compras = 
  			  this.compraService.listaCompraPorUsuario(3);
	  	  if(compras != null){
	  		  model.addAttribute("menu",this.compraService.getMenuColeccion(compras));
	  	  }
			
		model.addAttribute("publicaciones", lista);
		return "publicaciones";
	   }
	
	@RequestMapping(value="/publicacion/{id}",method = RequestMethod.GET)
	   public String getPublicacionHTML(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		log.info("Cargando publicacion.");
		log.info("Cargando Service publicacion:");
		String url = this.publicacionService.getPublicacion(Integer.valueOf(id).intValue()).getUrlArchivo();
		log.info("URL Encontrada: "+url);
		model.addAttribute("urlPublicacion", url);
		List<Compra> compras = 
	  			  this.compraService.listaCompraPorUsuario(3);
		  	  if(compras != null){
		  		  model.addAttribute("menu",this.compraService.getMenuColeccion(compras));
		  	  }
		return "publicacionHTML";
	   }
	
}
