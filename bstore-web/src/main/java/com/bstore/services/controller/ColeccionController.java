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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.ColeccionService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.validator.ColeccionValidator;

@Controller
public class ColeccionController {
	private final Logger log = Logger.getLogger(ColeccionController.class);
	private static final String NAME_CONTROLLER = "[--ColeccionController--]";

	@Autowired
	private ColeccionService coleccionService;
	
	@Autowired
	private ColeccionValidator coleccionValidator;
	
	@Autowired
	private UsuarioService usuarioService;

	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(coleccionValidator);
	}
	
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

	@RequestMapping(value="/coleccionAdmin",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Colecciones getAll"+NAME_CONTROLLER);
		HttpSession session = (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Coleccion> lista = this.coleccionService.getAll();
			log.info("Total colecciones encontradas: "+lista.size());
			//procesando usuarios
			for(Coleccion col: lista){
				Usuario u = this.usuarioService.byIdUser(col.getIdUsuarioUmodif());
				if(u!=null){
					col.setUsuario(u.getEmail());
				}
			}
			model.addAttribute("colecciones", lista);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "coleccionAdmin";
	}

	@RequestMapping(value="/coleccion/add", method = RequestMethod.GET)
	public String coleccionAdd(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("coleccionController.coleccionAdd(): "+NAME_CONTROLLER+"/add");
		log.info("---------------------------------------------------------------------------------");

		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			model.addAttribute("coleccion", new Coleccion());
		}else{
			response.sendRedirect(request.getContextPath());
		}

		return "coleccionAdd";
	}
	
	@RequestMapping(value="/coleccion/saveColeccion",method = RequestMethod.POST)
	public String saveColeccion(Model model, HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("coleccion") @Validated Coleccion coleccion,
			BindingResult result) throws IOException{
		log.info("ColeccionController.coleccionAdd(): "+NAME_CONTROLLER+"/saveColeccion");
		
		if(result.hasErrors()){
			return "coleccionAdd";
		}
		
//		log.info("coleccionObject: "+coleccion.toString());
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
//			if(publicacion!=null){
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				
				coleccion.setFechaUmodif(new Date());
				coleccion.setIdUsuarioUmodif(usuario.getId());
				
				log.info("Antes de guardar coleccion: "+coleccion.toString());
				
				coleccionService.saveOrUpdate(coleccion);
				
				//Para regresar a lista de publicaciones
				List<Coleccion> coleccionList = coleccionService.getAll();
				model.addAttribute("colecciones", coleccionList);	
//			}
//			else{
//				log.info("La nueva editorial es null, regresamos a la misma pantalla");
//				return "editorialAdd";
//			}
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "redirect:/coleccionAdmin";
//		return "coleccionAdmin";
	}

	@RequestMapping(value="/coleccion/delete/{id}", method = RequestMethod.GET)
	public String coleccionDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("coleccionController.coleccionDelete(): "+NAME_CONTROLLER+"/delete");
		log.info("");

		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
//			model.addAttribute("coleccion", new Coleccion());
		}else{
//			response.sendRedirect(request.getContextPath());
		}

		return "redirect:/coleccionAdmin";
	}
}
