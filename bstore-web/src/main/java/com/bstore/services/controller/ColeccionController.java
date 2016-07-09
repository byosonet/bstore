package com.bstore.services.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.service.ColeccionService;

@Controller
public class ColeccionController {
	private final Logger log = Logger.getLogger(ColeccionController.class);
	private static final String NAME_CONTROLLER = "[--ColeccionController--]";
	
	@Autowired
	private ColeccionService coleccionService;
	
	@RequestMapping(value="/colecciones",method = RequestMethod.GET)
	   public String colecciones(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Cargando colecciones. "+NAME_CONTROLLER);
		HttpSession session = (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Coleccion> lista = this.coleccionService.getColeccionDao(true);
			log.info("Total colecciones encontradas: "+lista.size());
			model.addAttribute("colecciones", lista);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "colecciones";
	   }
	
}
