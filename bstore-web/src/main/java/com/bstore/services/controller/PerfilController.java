package com.bstore.services.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;

@Controller
public class PerfilController {
	private final Logger log = Logger.getLogger(PerfilController.class);
	
	
	@RequestMapping(value="/perfil",method = RequestMethod.GET)
	   public String novedades(Model model, HttpServletRequest request) {
		log.info("Cargando perfil de usuario.");
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		model.addAttribute("menu",menu);
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario",usuario);

		return "perfilUsuario";
	   }

}
