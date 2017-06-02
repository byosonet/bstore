package com.bstore.services.controller;

import com.bstore.services.model.UserSession;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.EditorialService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.ValidarSesion;
import com.bstore.services.validator.EditorialValidator;

/**
 * 
 * @author hustler
 *
 */
@Controller
@RequestMapping("/editorial")
public class EditorialController {

	private Logger logger = Logger.getLogger(EditorialController.class);
	private static final String NAME_CONTROLLER="[EditorialController]";

	@Autowired
	private EditorialService editorialService;
	
	@Autowired
	private EditorialValidator editorialValidator;
	
	@Autowired
	private UsuarioService usuarioService;

	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(editorialValidator);
	}
	
	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("editorialController.getAll(): "+NAME_CONTROLLER+"/getAll");

		HttpSession session = (HttpSession) request.getSession(false);
		String result = ValidarSesion.validarSesionUsuarioActual(session);
		if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
			logger.info(ValidarSesion.MSG_FORBIDDEN);
			return "forbidden";
		}
		logger.info("Sesion activa Token === " + result);
		List<Editorial> editorialList = editorialService.getAll();
		if (editorialList != null) {
			for (Editorial edi : editorialList) {
				Usuario u = this.usuarioService.byIdUser(edi.getIdUsuarioUmodif());
				if (u != null) {
					edi.setUsuario(u.getEmail());
				}
			}
			model.addAttribute("editoriales", editorialList);
		}
		model.addAttribute("editorial", new Editorial());

		return "editoriales";
	}

	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String editorialAdd(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("editorialController.editorialAdd(): "+NAME_CONTROLLER+"/add");
		logger.info("---------------------------------------------------------------------------------");
		
		HttpSession session = (HttpSession) request.getSession(false);
		String result = ValidarSesion.validarSesionUsuarioActual(session);
		if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
			logger.info(ValidarSesion.MSG_FORBIDDEN);
			return "forbidden";
		}
		logger.info("Sesion activa Token === " + result);
		model.addAttribute("editorial", new Editorial());

		return "editorialAdd";
	}
	
	@RequestMapping(value="/saveEditorial",method = RequestMethod.POST)
	public String saveEditorial(Model model, HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("editorial") @Validated Editorial editorial,
			BindingResult result) throws IOException{
		logger.info("editorialController.editorialAdd(): "+NAME_CONTROLLER+"/saveEditorial");
		
		if(result.hasErrors()){
			
			return "editorialAdd";
		}
		
		logger.info("editorialObject: "+editorial);
		
		HttpSession session = (HttpSession) request.getSession(false);
		String results = ValidarSesion.validarSesionUsuarioActual(session);
		if (results.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
			logger.info(ValidarSesion.MSG_FORBIDDEN);
			return "forbidden";
		}
		logger.info("Sesion activa Token === " + results);
		if (editorial != null) {
			UserSession usuario = (UserSession) session.getAttribute("usuario");
			logger.info("Se va a guardar la nueva editorial");

			editorial.setFechaUmodif(new Date());
			editorial.setIdUsuarioUmodif(usuario.getId());
			editorialService.saveOrUpdate(editorial);
			List<Editorial> editorialList = editorialService.getAll();
			model.addAttribute("editoriales", editorialList);
		} else {
			logger.info("La nueva editorial es null, regresamos a la misma pantalla");
			return "editorialAdd";
		}

		return "editoriales";
	}
}
