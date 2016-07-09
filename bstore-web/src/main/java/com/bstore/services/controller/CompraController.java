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
	private final static String TYPE_CARD_VISA = "VISA";
	private final static String TYPE_CARD_MASTERCARD = "MASTERCARD";
	
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
		  
		  String tipoTarjeta = request.getParameter("visa") != null?TYPE_CARD_VISA:TYPE_CARD_MASTERCARD;
		  String nombre = request.getParameter("nombre");
		  String numeroTarjeta = request.getParameter("numeroTarjeta");
		  String cvv = request.getParameter("cvv");
		  String fechaExpriacionMes = request.getParameter("fechaExpiracionMes");
		  String fechaExpiracionAnio = request.getParameter("fechaExpiracionAnio");
		  String calle = request.getParameter("calle");
		  String colonia = request.getParameter("colonia");
		  String ciudad = request.getParameter("ciudad");
		  String estado = request.getParameter("estado");
		  String codigoPostal = request.getParameter("codigo");
		  String pais = request.getParameter("pais");
		  String tokenConekta = request.getParameter("key");
		  
		  System.out.println("tipoTarjeta: "+tipoTarjeta);
		  System.out.println("nombre: "+nombre);
		  System.out.println("numeroTarjeta: "+numeroTarjeta);
		  System.out.println("cvv: "+cvv);
		  System.out.println("fechaExpriacionMes: "+fechaExpriacionMes);
		  System.out.println("fechaExpiracionAnio: "+fechaExpiracionAnio);
		  System.out.println("calle: "+calle);
		  System.out.println("colonia: "+colonia);
		  System.out.println("ciudad: "+ciudad);
		  System.out.println("estado: "+estado);
		  System.out.println("codigoPostal: "+codigoPostal);
		  System.out.println("pais: "+pais);
		  System.out.println("tokenConekta: "+tokenConekta);
      	  
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
