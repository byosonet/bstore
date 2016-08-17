package com.bstore.services.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.PublicacionService;
import com.bstore.services.validator.PublicacionValidator;

@Controller
public class PublicacionController {
	private final Logger log = Logger.getLogger(PublicacionController.class);
	private static final String NAME_CONTROLLER="[--PublicacionController--]";
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private PublicacionValidator publicacionValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(publicacionValidator);
	}
	
	@RequestMapping(value="/coleccion/{id}",method = RequestMethod.GET)
	   public String coleccionesById(Model model, @PathVariable("id") String id, HttpServletRequest request, 
			   HttpServletResponse response) throws IOException{
		log.info("Cargando publicacion: "+NAME_CONTROLLER+"/coleccion/{id}");
		log.info("Cargando Service publicacion: "+NAME_CONTROLLER+"/coleccion/{id}");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
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
			Publicacion pub = this.publicacionService.getPublicacion(Integer.valueOf(id).intValue());
			log.info("URL Encontrada: "+pub.getUrlArchivo());
			model.addAttribute("urlPublicacion", pub.getUrlArchivo());
			model.addAttribute("nombrePublicacion", pub.getNombre());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		
		return "publicacionHTML";
	   }

	@RequestMapping(value="/publicacion/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("PublicacionController.getAll(): "+NAME_CONTROLLER+"/getAll");

		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Publicacion> publicacionList = publicacionService.getAll();
			if(publicacionList != null){
				model.addAttribute("publicaciones", publicacionList);
			}
			model.addAttribute("publicacion", new Publicacion());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "publicacionesAdmin";
	}
	
	@RequestMapping(value="/publicacion/add",method = RequestMethod.GET)
	public String publicacionAdd(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("publicacionController.publicacionAdd(): "+NAME_CONTROLLER+"/add");
		log.info("---------------------------------------------------------------------------------");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			model.addAttribute("publicacion", new Publicacion());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		
		return "publicacionAdd";
	}
	
	@RequestMapping(value="/publicacion/savePublicacion",method = RequestMethod.POST)
	public String savePublicacion(Model model, HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("publicacion") @Validated Publicacion publicacion,
			BindingResult result) throws IOException{
		log.info("publicacionController.publicacionAdd(): "+NAME_CONTROLLER+"/savePublicacion");
		
		if(result.hasErrors()){
			return "publicacionAdd";
		}
		
		log.info("publicacionObject: "+publicacion.toString());
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
//			if(publicacion!=null){
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				log.info("Se va a guardar la nueva publicacion");
				
				publicacion.setFechaUmodif(new Date());
				publicacion.setIdUsuarioUmodif(usuario.getId());
				publicacionService.saveOrUpdate(publicacion);
				
				//Para regresar a lista de publicaciones
				List<Publicacion> publicacionList = publicacionService.getAll();
				model.addAttribute("publicaciones", publicacionList);	
//			}
//			else{
//				log.info("La nueva editorial es null, regresamos a la misma pantalla");
//				return "editorialAdd";
//			}
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "publicacionesAdmin";
	}
}
