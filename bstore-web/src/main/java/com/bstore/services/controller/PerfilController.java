package com.bstore.services.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.model.ErrorService;
import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.PerfilService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.UtilService;

@Controller
public class PerfilController {
	private final Logger log = Logger.getLogger(PerfilController.class);
	private static final String NAME_CONTROLLER = "[--PerfilController--]";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping(value="/perfil",method = RequestMethod.GET)
	   public String perfil(Model model, HttpServletRequest request){
		log.info("Cargando perfil de usuario: "+NAME_CONTROLLER);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			model.addAttribute("usuario",usuario);
			return "perfilUsuario";
		}else{
			log.info("Enviando a login, token no existe");
			return "redirect:/index.jsp";
		}
	 }
	
	
	@RequestMapping(value="/perfil/actualizar",method = RequestMethod.POST)
	   public ResponseEntity<ErrorService> perfilActualizar(Model model, HttpServletRequest request, 
			   HttpServletResponse response) throws IOException{
		log.info("Actualizando perfil de usuario: "+NAME_CONTROLLER);
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorService responseLocal = new ErrorService();
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			String idUsuario = request.getParameter("idUsuario");
	        String nombreUsuario = request.getParameter("nombre");
	        String apaternoUsuario = request.getParameter("apaterno");
	        String amaternoUsuario = request.getParameter("amaterno");
	        String emailUsuario = request.getParameter("email");
	        String login = request.getParameter("login");
	        String passwordUsuario1 = request.getParameter("pass1");
	        String dia = request.getParameter("dia");
	        String mes = request.getParameter("mes");
	        String anio = request.getParameter("anio");
	        String actividad = request.getParameter("actividad");
	        String sexo = request.getParameter("sexo");
	        String notificar = request.getParameter("notificar");
	        
	        Usuario user = this.usuarioService.byIdUser(Integer.valueOf(idUsuario));
	        Usuario userTemporal = this.usuarioService.validaEmailSistema(emailUsuario);
	        Usuario userTemporalLogin = this.usuarioService.validaLoginSistema(login);
	        if(userTemporal!=null && !user.getEmail().equalsIgnoreCase(userTemporal.getEmail())){
	        	responseLocal.setCodigo("404");
	        	responseLocal.setMensaje("Este email ya ha sido utilizado, "+emailUsuario);
	        } else if(userTemporalLogin!=null && !user.getLogin().equalsIgnoreCase(userTemporalLogin.getLogin())){
	        	responseLocal.setCodigo("404");
	        	responseLocal.setMensaje("Este login ya ha sido utilizado, "+login);
	        } else if(user!=null){
	            this.log.info(" -- Usuario encontrado: "+user.toString());
	            if(!user.getPassword().equalsIgnoreCase(passwordUsuario1)){
	            	String encriptarPassword = UtilService.Encriptar(passwordUsuario1);
	            	user.setPassword(encriptarPassword);
	            }
	            user.setNombre(nombreUsuario);
	            user.setEmail(emailUsuario);
	            user.setLogin(login);
	            user.setAPaterno(apaternoUsuario);
	            user.setAMaterno(amaternoUsuario);
	            user.setActividad(actividad);
	            user.setSexo(sexo);
	            user.setNotificaciones(notificar!=null?notificar:"NO");
	            user.setUltConexion(new Date());
	            
	            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	            String dateInString = dia+"/"+mes+"/"+anio;
	            try {
	                Date date = formatter.parse(dateInString);
	                this.log.info(" -- Año de Nacimiento: " + date);
	                if (LoginController.validaFecha(dateInString)) {
	                    this.log.info(" -- Fecha es Valida: " + date);
	                    user.setFechaNacimiento(date);
	                } else {
	                    this.log.info(" -- Fecha Invalida: " + dateInString);
	                    ErrorService data = new ErrorService();
	                    data.setCodigo("404");
	                    data.setMensaje("La fecha de nacimiento es inválida: "+dateInString);
	                    return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
	                }
	            } catch (ParseException e) {
	                this.log.error(" -- Error al crear la fecha de nacimiento: " + e.getMessage());
	            }
	            
	            this.usuarioService.actualizarDatosUsuario(user);
	            
	            this.log.info(" -- El usuario fue actualizado");
	            responseLocal.setCodigo("200");
	            responseLocal.setMensaje("La información fue actualizada con éxito.");
	            status = HttpStatus.OK;
	            session.setAttribute("usuario", user);
	            model.addAttribute("usuario",user);
	        }
		}else{
			response.sendRedirect(request.getContextPath());
		}
        return new ResponseEntity<ErrorService>(responseLocal, status);
	   }
	
	
	@RequestMapping(value="perfil/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("PerfilController.getAll(): "+NAME_CONTROLLER+"/getAll");

		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			List<Perfil> perfilList = perfilService.getAll();
			if(perfilList != null){
				model.addAttribute("perfiles", perfilList);
			}
			model.addAttribute("perfil", new Perfil());
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "perfilesAdmin";
	}
}
