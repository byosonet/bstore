package com.bstore.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.PublicacionService;


@Controller
public class CompraController {
	private final Logger log = Logger.getLogger(CompraController.class);
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(value="/comprar/publicacion/{id}",method = RequestMethod.GET)
	   public String getDetalleCompra(Model model, @PathVariable("id") int id, HttpServletRequest request) {
		log.info("Cargando detalle de compra");
		
		Publicacion publicacion = this.publicacionService.getPublicacion(id);
		model.addAttribute("publicacion", publicacion);
		model.addAttribute("menu",this.compraService.getMenuColeccion(3));
		
		return "detalleCompra";
	   }
	
}
