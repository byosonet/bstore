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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.EditorialService;

/**
 * 
 * @author hustler
 *
 */
@Controller
@RequestMapping("/editorial")
public class EditorialController {

	private Logger logger = Logger.getLogger(EditorialController.class);
	private static final String NAME_CONTROLLER="[--EditorialController--]";

	@Autowired
	private EditorialService editorialService;

	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("editorialController.getAll(): "+NAME_CONTROLLER+"/getAll");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Editorial> editorialList = editorialService.getAll();
			if(editorialList != null){
				model.addAttribute("editoriales",editorialList);
			}
			
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			 model.addAttribute("menu",menu);
			logger.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "editoriales";
	}

	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String editorialAdd(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("editorialController.editorialAdd(): "+NAME_CONTROLLER+"/add");
		logger.info("---------------------------------------------------------------------------------");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			 model.addAttribute("menu",menu);
			logger.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		
		return "editorialAdd";
	}
	
	@RequestMapping(value="/saveEditorial",method = RequestMethod.POST)
	public String saveEditorial(Model model, HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("editorialForm") Editorial editorial) throws IOException{
		logger.info("editorialController.editorialAdd(): "+NAME_CONTROLLER+"/saveEditorial");
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			@SuppressWarnings("unchecked")
			Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
			 model.addAttribute("menu",menu);
			logger.info("Recuperando de sesion menu: "+session.getAttribute("menu").toString());
			logger.info("nombre de la nueva editorial: "+editorial.getNombre());
			//Para regresar a lista de editoriales
			List<Editorial> editorialList = editorialService.getAll();
			if(editorialList != null){
				model.addAttribute("editoriales",editorialList);
			}
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "editoriales";
	}
}
