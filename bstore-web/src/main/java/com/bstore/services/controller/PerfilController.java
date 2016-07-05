package com.bstore.services.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.UtilService;

@Controller
public class PerfilController {
	private final Logger log = Logger.getLogger(PerfilController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/perfil",method = RequestMethod.GET)
	   public String perfil(Model model, HttpServletRequest request) {
		log.info("Cargando perfil de usuario.");
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		model.addAttribute("menu",menu);
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		model.addAttribute("usuario",usuario);

		return "perfilUsuario";
	   }
	
	
	@RequestMapping(value="/perfil/actualizar",method = RequestMethod.POST)
	   public ResponseEntity<ErrorService> perfilActualizar(Model model, HttpServletRequest request) {
		log.info("Actualizando perfil de usuario.");
		
		HttpSession session= (HttpSession) request.getSession();
		@SuppressWarnings("unchecked")
		Map<Coleccion, List<Publicacion>> menu= (Map<Coleccion, List<Publicacion>>) session.getAttribute("menu");
		model.addAttribute("menu",menu);
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		String idUsuario = request.getParameter("idUsuario");
        String nombreUsuario = request.getParameter("nombre");
        String apaternoUsuario = request.getParameter("apaterno");
        String amaternoUsuario = request.getParameter("amaterno");
        String emailUsuario = request.getParameter("email");
        String passwordUsuario1 = request.getParameter("pass1");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String actividad = request.getParameter("actividad");
        String sexo = request.getParameter("sexo");
        String notificar = request.getParameter("notificar");
        
        ErrorService response = new ErrorService();
        Usuario user = this.usuarioService.byIdUser(Integer.valueOf(idUsuario));
        Usuario userTemporal = this.usuarioService.validaEmailSistema(emailUsuario);
        if(userTemporal!=null && !user.getEmail().equalsIgnoreCase(userTemporal.getEmail())){
        	response.setCodigo("404");
            response.setMensaje("Este email ya ha sido utilizado, "+emailUsuario);
        } else if(user!=null){
            this.log.info(" -- Usuario encontrado: "+user.toString());
            if(!user.getPassword().equalsIgnoreCase(passwordUsuario1)){
            	String encriptarPassword = UtilService.Encriptar(passwordUsuario1);
            	user.setPassword(encriptarPassword);
            }
            user.setNombre(nombreUsuario);
            user.setEmail(emailUsuario);
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
            response.setCodigo("200");
            response.setMensaje("La información fue actualizada con éxito.");
            status = HttpStatus.OK;
            session.setAttribute("usuario", user);
            model.addAttribute("usuario",user);
        }
        return new ResponseEntity<ErrorService>(response, status);
	   }
}
