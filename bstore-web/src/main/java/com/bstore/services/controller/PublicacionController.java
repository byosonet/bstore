package com.bstore.services.controller;

import com.bstore.services.model.UserSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
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

import com.bstore.services.persistence.pojo.Anexo;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Properties;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.PropertyService;
import com.bstore.services.service.PublicacionService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.ValidarSesion;
import com.bstore.services.validator.PublicacionValidator;
import java.util.ArrayList;

@Controller
public class PublicacionController {

    private final Logger log = Logger.getLogger(PublicacionController.class);
    private static final String NAME_CONTROLLER = "[PublicacionController]";
    private static final String KEY_COPY_PASTE = "com.copy.paste.text";

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private PublicacionValidator publicacionValidator;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private CompraService compraService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(publicacionValidator);
    }

    @RequestMapping(value = "/coleccion/{id}", method = RequestMethod.GET)
    public String coleccionesById(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");

        int idGenerado = 0;
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try {
            idGenerado = Integer.valueOf(id);
            lista = this.publicacionService.getPublicacionesByColeccionID(Integer.valueOf(id).intValue(),
                    usuario.getId());
             log.info("Total publicaciones encontradas: " + lista.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }

        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }

    @RequestMapping(value = "/coleccion/tema/asc/{id}", method = RequestMethod.GET)
    public String coleccionesByNombreAsc(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        
        int idGenerado = 0;
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            idGenerado = Integer.valueOf(id);
            lista = this.publicacionService.getPublicacionesByNombreAsc(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/tema/asc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }

    @RequestMapping(value = "/coleccion/tema/desc/{id}", method = RequestMethod.GET)
    public String coleccionesByNombreDesc(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        
        int idGenerado = 0;
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            idGenerado = Integer.valueOf(id);
            lista = this.publicacionService.getPublicacionesByNombreDesc(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/tema/desc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }

    @RequestMapping(value = "/coleccion/precio/asc/{id}", method = RequestMethod.GET)
    public String coleccionesByPrecioAsc(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        
        int idGenerado = 0;
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            idGenerado = Integer.valueOf(id);
            lista = this.publicacionService.getPublicacionesByPrecioAsc(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/precio/asc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }

    @RequestMapping(value = "/coleccion/precio/desc/{id}", method = RequestMethod.GET)
    public String coleccionesByPrecioDesc(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        
        int idGenerado = 0;
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            idGenerado = Integer.valueOf(id);
            lista = this.publicacionService.getPublicacionesByPrecioDesc(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/precio/desc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }
    
    @RequestMapping(value = "/coleccion/comprados/{id}", method = RequestMethod.GET)
    public String coleccionesCompradas(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            lista = this.publicacionService.getPublicacionesCompradas(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/precio/desc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }

    @RequestMapping(value = "/coleccion/gratis/{id}", method = RequestMethod.GET)
    public String coleccionesGratis(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            lista = this.publicacionService.getPublicacionesGratis(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/precio/desc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }
    
    @RequestMapping(value = "/coleccion/porcomprar/{id}", method = RequestMethod.GET)
    public String coleccionesPorComprar(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/coleccion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        List<Publicacion> lista = new ArrayList<Publicacion>();
        try{
            lista = this.publicacionService.getPublicacionesPorComprar(Integer.valueOf(id).intValue(),
                usuario.getId());
        log.info("Total publicaciones encontradas: " + lista.size());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /coleccion/precio/desc/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        model.addAttribute("publicaciones", lista);
        model.addAttribute("coleccionId", id);

        return "publicaciones";
    }



    @RequestMapping(value = "/publicacion/{id}", method = RequestMethod.GET)
    public String getPublicacionHTML(Model model, @PathVariable("id") String id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        log.info("Cargando publicacion: " + NAME_CONTROLLER + "/publicacion/{id}");
        log.info("Cargando Service publicacion: " + NAME_CONTROLLER + "/publicacion/{id}");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
         UserSession usuario = (UserSession) session.getAttribute("usuario");
        List<Compra> compras = this.compraService.obtenetComprasbyUsuario(usuario.getId());
        
        int idGenerado = 0;
        boolean encontrado = false;
        Publicacion pub = new Publicacion();
        try{
            idGenerado = Integer.valueOf(id);
            
            if (compras != null && compras.size() > 0) {
                log.info("Compras: "+compras.toString());
                for (Compra compra : compras) {
                    if (idGenerado == compra.getId().getIdPublicacion()) {
                        encontrado = true;
                        break;
                    }
                }
            }
            
            if (!encontrado) {
                log.info("Error al procesar: /publicacion/{id}" + id);
                model.addAttribute("mensajeError", "Lo sentimos para poder ver está publicación, primero debes comprarla.");
                return "muestraError";
            }
            
            pub = this.publicacionService.getPublicacion(Integer.valueOf(id).intValue());
            model.addAttribute("nombrePublicacion", pub.getNombre());
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /publicacion/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        /**
         * Recuperando Anexos
         */
        List<Anexo> anexos = this.publicacionService.buscarAnexos(pub.getId());
        if (anexos != null && anexos.size() > 0) {
            for (Anexo a : anexos) {
                if (a.getImagen() != null) {
                    this.log.info("Imagen encontrada: " + a.getOrigenImagen());
                    byte[] bytes = a.getImagen();
                    byte[] encodeBase64 = Base64.encodeBase64(bytes);
                    String base64Encoded = new String(encodeBase64, "UTF-8");
                    a.setResultImage(base64Encoded);
                    this.log.info("Encode generado correctamente para: " + a.getOrigenImagen());
                }
                if (a.getImagenZoom() != null) {
                    this.log.info("Imagen encontrada Zoom: " + a.getOrigenImagenZoom());
                    byte[] bytes = a.getImagenZoom();
                    byte[] encodeBase64 = Base64.encodeBase64(bytes);
                    String base64Encoded = new String(encodeBase64, "UTF-8");
                    a.setResultImageZoom(base64Encoded);
                    this.log.info("Encode generado correctamente para Imagen Zoom: " + a.getOrigenImagenZoom());
                }
            }
            model.addAttribute("anexos", anexos);
            Properties prop = this.propertyService.getValueKey(KEY_COPY_PASTE);
            model.addAttribute("copyright", prop != null ? prop.getValue() : "");

        }

        return "publicacionHTML";
    }

    @RequestMapping(value = "/publicacion/getAll", method = RequestMethod.GET)
    public String getAll(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("PublicacionController.getAll(): " + NAME_CONTROLLER + "/getAll");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        List<Publicacion> publicacionList = publicacionService.getAll();
        if (publicacionList != null) {
            for (Publicacion pub : publicacionList) {
                Usuario u = this.usuarioService.byIdUser(pub.getIdUsuarioUmodif());
                if (u != null) {
                    pub.setUsuarioMail(u.getEmail());
                }
            }
            model.addAttribute("publicaciones", publicacionList);
        }
        model.addAttribute("publicacion", new Publicacion());

        return "publicacionesAdmin";
    }

    @RequestMapping(value = "/publicacion/add", method = RequestMethod.GET)
    public String publicacionAdd(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("publicacionController.publicacionAdd(): " + NAME_CONTROLLER + "/add");
        log.info("---------------------------------------------------------------------------------");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        model.addAttribute("publicacion", new Publicacion());

        return "publicacionAdd";
    }

    @RequestMapping(value = "/publicacion/savePublicacion", method = RequestMethod.POST)
    public String savePublicacion(Model model, HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("publicacion") @Validated Publicacion publicacion,
            BindingResult result) throws IOException {
        log.info("publicacionController.publicacionAdd(): " + NAME_CONTROLLER + "/savePublicacion");

        if (result.hasErrors()) {
            return "publicacionAdd";
        }

        log.info("publicacionObject: " + publicacion.toString());

        HttpSession session = (HttpSession) request.getSession(false);
        String results = ValidarSesion.validarSesionUsuarioActual(session);
        if (results.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + results);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        log.info("Se va a guardar la nueva publicacion");

        publicacion.setFechaUmodif(new Date());
        publicacion.setIdUsuarioUmodif(usuario.getId());
        publicacionService.saveOrUpdate(publicacion);
        List<Publicacion> publicacionList = publicacionService.getAll();
        model.addAttribute("publicaciones", publicacionList);

        return "publicacionesAdmin";
    }

    @RequestMapping(value = "/publicacion/search", method = RequestMethod.GET)
    public String search(Model model, HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("publicacion") @Validated Publicacion publicacion) throws IOException {
        log.info("PublicacionController.search(): " + NAME_CONTROLLER + "/search; publicacion es: " + publicacion != null ? publicacion.toString() : "Publicacion es NULL!!!");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        List<Publicacion> publicacionList = publicacionService.search(publicacion);
        if (publicacionList != null) {
            model.addAttribute("publicaciones", publicacionList);
        }
        model.addAttribute("publicacion", new Publicacion());

        return "publicacionesAdmin";
    }
}
