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
import com.bstore.services.service.CompraService;
import com.bstore.services.service.PublicacionService;


@Controller
public class CompraController {
	private final Logger log = Logger.getLogger(CompraController.class);
	private static final String NAME_CONTROLLER = "[--CompraController--]";
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private CompraService compraService;
	
	
	@RequestMapping(value="/comprar/publicacion/{id}",method = RequestMethod.GET)
	   public String getDetalleCompra(Model model, @PathVariable("id") int id, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Cargando detalle de compra: "+NAME_CONTROLLER);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			model.addAttribute("menu",menu);
			log.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
			
			Publicacion publicacion = this.publicacionService.getPublicacion(id);
			model.addAttribute("publicacion", publicacion);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "detalleCompra";
	   }
	
	@RequestMapping(value="/pagar/publicacion/{id}",method = RequestMethod.POST)
	   public String pagarPublicacion(Model model, @PathVariable("id") int id, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Controller::: "+NAME_CONTROLLER);
		log.info("Procesar compra de publicacion con ID::: "+id);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
		  Usuario usuario = (Usuario) session.getAttribute("usuario");
      	  
		  Map<Coleccion, List<Publicacion>> menu = this.compraService.getMenuColeccion(usuario.getId());
      	  model.addAttribute("menu",menu);
      	  
      	  List<Publicacion> ultimasCompras = this.compraService.ultimasCompras(usuario.getId());
      	  model.addAttribute("ultimasCompras",ultimasCompras);
      	  
      	  session.setAttribute("ultimasCompras", ultimasCompras);
      	  session.setAttribute("menu",menu);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "pagoCompra";
	   }
	
}
