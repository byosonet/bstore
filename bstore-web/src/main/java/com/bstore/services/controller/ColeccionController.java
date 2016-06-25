package com.bstore.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.service.ColeccionService;
import com.bstore.services.service.CompraService;

@Controller
public class ColeccionController {
	private final Logger log = Logger.getLogger(ColeccionController.class);
	
	@Autowired
	private ColeccionService coleccionService;
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(value="/colecciones",method = RequestMethod.GET)
	   public String novedades(Model model, HttpServletRequest request) {
		log.info("Cargando novedades.");
		log.info("Cargando Service Coleccion:");
		List<Coleccion> lista = this.coleccionService.getColeccionDao(true);
		log.info("Total colecciones encontradas: "+lista.size());
		model.addAttribute("colecciones", lista);
		List<Compra> compras = 
	  			  this.compraService.listaCompraPorUsuario(3);
	  	  if(compras != null){
	  		  model.addAttribute("menu",this.compraService.getMenuColeccion(compras));
	  	  }
		return "colecciones";
	   }
	
}
