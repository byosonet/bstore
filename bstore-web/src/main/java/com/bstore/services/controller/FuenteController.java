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

import com.bstore.services.persistence.pojo.Fuente;
import com.bstore.services.service.FuenteService;

/**
 * 
 * @author hustler
 *
 */
@Controller
@RequestMapping("/fuente")
public class FuenteController {

	private Logger logger = Logger.getLogger(FuenteController.class);
	private static final String NAME_CONTROLLER="[--FuenteController--]";

	@Autowired
	private FuenteService fuenteService;

	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("fuenteController.getAll(): "+NAME_CONTROLLER+"/getAll");

		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Fuente> fuenteList = fuenteService.getAll();
			if(fuenteList != null){
				model.addAttribute("fuentes",fuenteList);
			}
			model.addAttribute("fuente", new Fuente());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "fuentesAdmin";
	}
}
