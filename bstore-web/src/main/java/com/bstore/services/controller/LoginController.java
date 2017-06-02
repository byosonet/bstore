package com.bstore.services.controller;

import com.bstore.services.drools.DroolRuleAge;
import com.bstore.services.drools.vo.UserTemp;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.model.ErrorService;
import com.bstore.services.model.MenuModel;
import com.bstore.services.model.PublicacionActiva;
import com.bstore.services.model.UserSession;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.EnviarEmailService;
import com.bstore.services.service.PropertyService;
import com.bstore.services.service.PublicacionService;
import com.bstore.services.service.UsuarioService;
import com.bstore.services.util.UtilService;
import com.bstore.services.util.ValidarSesion;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gtrejo
 */
@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CompraService compraService;

    @Autowired
    PublicacionService publicacionService;

    @Autowired
    private DroolRuleAge droolRuleAgeAdapter;

    @Autowired
    EnviarEmailService enviarEmailService;

    @Autowired
    private PropertyService propertyService;

    private final Logger log = Logger.getLogger(LoginController.class);
    private final String EMAIL_SYSTEM = "com.bstore.mail.app.bcc";
    private final String DOMAIN_BSTORE = "com.domain.quivira";

    private final String USER_EMAIL_SYSTEM = "com.email.system";
    private final String PASSWORD_EMAIL_SYSTEM = "com.password.system";

    @RequestMapping(value = "/equivira", method = RequestMethod.POST)

    public String ingresar(Model model, HttpServletRequest request) throws Exception {
        log.info("URL: value=/equivira,method = RequestMethod.POST");

        HttpSession session = request.getSession(true);
        String requestEmail = request.getParameter("user");
        String requestPassword = request.getParameter("password");
        this.log.info("Ingresando al sistema");
        this.log.info("Request: " + request);
        this.log.info("User: " + requestEmail);
        this.log.info("Password: " + requestPassword);

        if (requestEmail != null && requestPassword != null) {
            String user = requestEmail;
            String password = requestPassword;
            String encriptarPassword = UtilService.Encriptar(password);
            UserSession usuario = this.usuarioService.validaUsuarioModel(user, encriptarPassword);
            if (usuario != null) {
                this.log.info("Ingresando al sistema como: " + usuario.getNombre());
                try {
                    String cifrar = UtilService.Encriptar(usuario.getEmail() + ";" + password);
                    model.addAttribute("cifrar", cifrar);
                    model.addAttribute("user", usuario.getNombre());
                    List<MenuModel> menu = this.compraService.getMenuColeccion(usuario.getId());
                    List<Publicacion> ultimasCompras = this.compraService.ultimasCompras(usuario.getId());
                    List<PublicacionActiva> publicacionesActivas = this.publicacionService.getPublicacionesActivasModel(usuario.getId());                                        
                    model.addAttribute("publicacionesActivas", publicacionesActivas);
                    session.setAttribute("compras", ultimasCompras!=null && ultimasCompras.size()>0 ? true:false);
                    session.setAttribute("menu", menu);
                    session.setAttribute("usuario", usuario);
                    session.setAttribute("token", cifrar);
                    session.setAttribute("userName", usuario.getEmail());
                    return "indexPrincipal";
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return "invalido";
    }

    @RequestMapping(value = "/equivira", method = RequestMethod.GET)
    public String ingresarGET(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        @SuppressWarnings("unchecked")
        List<MenuModel> menu = (List<MenuModel>) session.getAttribute("menu");
        model.addAttribute("menu", menu);
        log.info("Recuperando de sesion menu: " + session.getAttribute("menu").toString());
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        List<PublicacionActiva> publicacionesActivas = this.publicacionService.getPublicacionesActivasModel(usuario.getId());                                        
        model.addAttribute("publicacionesActivas", publicacionesActivas);
        return "indexPrincipal";
    }

    @RequestMapping(value = "/validar/usuario", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> validar(Model model, HttpServletRequest request) {
        this.log.info("Ingresando al sistema");
        this.log.info("Request: " + request.toString());
        this.log.info("User: " + request.getParameter("user"));
        this.log.info("Password: " + request.getParameter("password"));

        if (request.getParameter("user") != null && request.getParameter("password") != null) {
            String user = request.getParameter("user");
            String password = request.getParameter("password");

            String encriptarPassword = UtilService.Encriptar(password);
            Usuario usuario = this.usuarioService.validaUsuario(user, encriptarPassword);

            if (usuario != null) {
                this.log.info("Usuario correcto");
                ErrorService data = new ErrorService();
                data.setCodigo("200");
                data.setMensaje("Hola Bienvenido: " + usuario.getNombre());
                try {
                    this.usuarioService.actulizarConexionUsuario(usuario);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return new ResponseEntity<ErrorService>(data, HttpStatus.OK);
            }
        }

        this.log.info("Usuario incorrecto");
        ErrorService data = new ErrorService();
        data.setCodigo("404");
        data.setMensaje("Esta email y password no ha sido registrado: " + request.getParameter("user"));
        return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/registrar/usuario", method = RequestMethod.GET)
    public String registrarUsuario(Model model) {
        model.addAttribute("menu", "smenu");
        return "registrar";
    }

    @RequestMapping(value = "/confirmarTuCuenta", method = RequestMethod.GET)
    public String confirmarTuCuenta(HttpServletRequest request, Model model) {
        String token = "";
        try {
            token = request.getParameter("token");
            this.log.info("Token recibido =" + token);
            String email = UtilService.Desencriptar(token);
            this.log.info("Email: " + email);
            Usuario usuario = this.usuarioService.validaEmailSistema(email);
            if (usuario != null) {
                usuario.setEstatus(1);
                this.usuarioService.actualizarDatosUsuario(usuario);
                model.addAttribute("activado", true);
                model.addAttribute("email", email);
                this.log.info("Activacion de cuenta correcta para: " + email);
                HttpSession session = (HttpSession) request.getSession(false);
                session.removeAttribute("menu");
                session.removeAttribute("usuario");
                session.removeAttribute("token");
                session.removeAttribute("compras");
                session.removeAttribute("userName");
                session.removeAttribute("publicacionesActivas");
                session.invalidate();
                log.info("Removiendo datos de la session");
            } else {
                model.addAttribute("activado", false);
                model.addAttribute("emailApp", this.propertyService.getValueKey(EMAIL_SYSTEM).getValue());
                this.log.info("No se puede activar cuenta email/token invalido: " + email);
            }
        } catch (Exception ex) {
            this.log.error("Error al descifrar el token para activacion de cuenta ===-> " + token);
            model.addAttribute("activado", false);
            model.addAttribute("emailApp", this.propertyService.getValueKey(EMAIL_SYSTEM).getValue());
            ex.printStackTrace();
        }
        return "activacion";
    }

    @RequestMapping(value = "/confirmarBajaDeTuCuenta", method = RequestMethod.GET)
    public String confirmarBajaDeTuCuenta(HttpServletRequest request, Model model) {
        String token = "";
        try {
            token = request.getParameter("token");
            this.log.info("Token recibido =" + token);
            String email = UtilService.Desencriptar(token);
            this.log.info("Email: " + email);
            Usuario usuario = this.usuarioService.validaEmailSistema(email);
            if (usuario != null) {
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("Fecha de Baja::: " + stamp);
                Date fechaBaja = new Date(stamp.getTime());
                usuario.setEstatus(2);
                usuario.setFechaBaja(fechaBaja);
                this.usuarioService.actualizarDatosUsuario(usuario);
                model.addAttribute("baja", true);
                model.addAttribute("email", email);
                this.log.info("Cancelacion de cuenta correcta para: " + email);
                HttpSession session = (HttpSession) request.getSession(false);
                session.removeAttribute("menu");
                session.removeAttribute("usuario");
                session.removeAttribute("token");
                session.removeAttribute("compras");
                session.removeAttribute("userName");
                session.removeAttribute("publicacionesActivas");
                session.invalidate();
                log.info("Removiendo datos de la session");
            } else {
                model.addAttribute("baja", false);
                model.addAttribute("emailApp", this.propertyService.getValueKey(EMAIL_SYSTEM).getValue());
                this.log.info("No se puede desactivar cuenta email/token invalido: " + email);
            }
        } catch (Exception ex) {
            this.log.error("Error al descifrar el token para desactivacion de cuenta ===-> " + token);
            model.addAttribute("baja", false);
            model.addAttribute("emailApp", this.propertyService.getValueKey(EMAIL_SYSTEM).getValue());
            ex.printStackTrace();
        }
        return "cancelar";
    }

    @RequestMapping(value = "/usuario/nuevo", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> registrarUsuarionNuevo(HttpServletRequest request, Model model) {
        String notificar = request.getParameter("notificar") != null ? request.getParameter("notificar") : "NO";
        String condiciones = request.getParameter("condiciones") != null ? request.getParameter("condiciones") : "NO";
        String nombre = request.getParameter("nombre").toUpperCase();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String sexo = request.getParameter("sexo");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String actividad = request.getParameter("actividad");
        String apaterno = request.getParameter("apaterno").toUpperCase();
        String amaterno = request.getParameter("amaterno").toUpperCase();
        String login = request.getParameter("login");
        String telefono = request.getParameter("telefono");

        Usuario user = this.usuarioService.validaEmailSistema(email);
        Usuario userLogin = this.usuarioService.validaLoginSistema(login);
        if (user == null) {
            if (userLogin != null) {
                this.log.info("Usuario ya se encuentra en sistema registrado");
                ErrorService data = new ErrorService();
                data.setCodigo("404");
                data.setMensaje("Este login ya ha sido registrado con anterioridad: " + login);
                return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
            }
            this.log.info("Agregando nuevo usuario:");
            this.log.info("Nombre: " + nombre);
            this.log.info("Email: " + email);
            this.log.info("Password: " + password);
            this.log.info("Sexo: " + sexo);
            this.log.info("Acepto condiciones: " + condiciones);
            this.log.info("Notificar: " + notificar);
            this.log.info("Dia: " + dia);
            this.log.info("Mes: " + mes);
            this.log.info("Anio: " + anio);
            this.log.info("Actividad: " + actividad);
            this.log.info("Apaterno: " + apaterno);
            this.log.info("Amaterno: " + amaterno);
            this.log.info("Login: " + login);
            this.log.info("Telefono: " + telefono);

            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            this.log.info("Fecha de Alta::: " + stamp);
            Date fechaAlta = new Date(stamp.getTime());
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setSexo(sexo);
            usuario.setAceptoTerminos(condiciones);
            String encriptado = UtilService.Encriptar(password);
            usuario.setPassword(encriptado);
            usuario.setFechaAlta(fechaAlta);
            usuario.setUltConexion(fechaAlta);
            usuario.setNotificaciones(notificar);
            usuario.setActividad(actividad);
            usuario.setAPaterno(apaterno);
            usuario.setAMaterno(amaterno);
            usuario.setTelefono(telefono);
            usuario.setLogin(login);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dia + "/" + mes + "/" + anio;
            try {
                Date date = formatter.parse(dateInString);
                this.log.info("Año de Nacimiento: " + date);
                if (LoginController.validaFecha(dateInString)) {
                    this.log.info("Fecha es Valida: " + date);
                    usuario.setFechaNacimiento(date);
                } else {
                    this.log.info("Fecha Invalida: " + dateInString);
                    ErrorService data = new ErrorService();
                    data.setCodigo("404");
                    data.setMensaje("La fecha de nacimiento es inválida: " + dateInString);
                    return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                this.log.error("Error al crear la fecha de nacimiento: " + e.getMessage());
            }
            int edad = UtilService.calcularEdad(dateInString);
            this.log.info("La edad del usuario a registrar es de: " + edad);
            this.log.info("Disparando las reglas con Drools");
            UserTemp userTemp = new UserTemp();
            userTemp.setEdad(edad);
            userTemp.setEmail(email);
            userTemp.setNombre(nombre);
            userTemp = this.droolRuleAgeAdapter.validarReglasAge(userTemp);
            if (userTemp.isValidado()) {
                this.log.info("El usuario ha validado todas las reglas");
            } else {
                this.log.info("El usuario no ha cumplido las reglas del sistema");
                ErrorService data = new ErrorService();
                data.setCodigo("404");
                data.setMensaje(userTemp.getMensaje());
                return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
            }
            this.usuarioService.agregaUsuarioNuevo(usuario);
            this.log.info("El usuario se agrego correctamente");
            try {
                try {
                    this.log.info("Url request actual::: " + request.getRequestURL());
                    String urlServer = this.propertyService.getValueKey(DOMAIN_BSTORE).getValue();
                    String emailEncriptado = UtilService.Encriptar(usuario.getEmail());
                    String nuevaUrlParaConfirmacion = urlServer + "/"
                            + "confirmarTuCuenta?token=" + emailEncriptado;
                    this.log.info("Url para activacion de cuenta " + usuario.getEmail() + " URL === " + nuevaUrlParaConfirmacion);
                    this.enviarEmailService.enviarEmailRegistro(usuario.getEmail(), this.propertyService.getValueKey(EMAIL_SYSTEM).getValue().split(";"), usuario.getNombre(), nuevaUrlParaConfirmacion,
                            this.propertyService.getValueKey(USER_EMAIL_SYSTEM).getValue(),
                            this.propertyService.getValueKey(PASSWORD_EMAIL_SYSTEM).getValue());
                    this.log.info("Enviado");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    this.log.error("No se puedo enviar mail de registro: " + ex.getMessage());
                }
                this.log.info("Usuario correcto");
                ErrorService data = new ErrorService();
                data.setCodigo("200");
                data.setMensaje("Para continuar debes de activar tu cuenta y para ello te hemos enviado un email a: " + usuario.getEmail());
                return new ResponseEntity<ErrorService>(data, HttpStatus.OK);
            } catch (Exception ex) {
                ex.printStackTrace();
                this.log.info("No se puedo llevar a cabo el registro del usuario en el sistema.");

            }
        }
        this.log.info("Usuario ya se encuentra en sistema registrado");
        ErrorService data = new ErrorService();
        data.setCodigo("404");
        data.setMensaje("Este email ya ha sido registrado con anterioridad o la cuenta esta desactivada: " + email);
        return new ResponseEntity<ErrorService>(data, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/localizar/email", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> localizarPorEmail(HttpServletRequest request) throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());
        this.log.info("JSON: " + jObj.toString());

        ErrorService response = new ErrorService();
        String email = jObj.getString("email");
        Usuario user = this.usuarioService.validaEmailSistema(email);
        if (user != null) {
            this.log.info("Usuario encontrado: " + user.getEmail());
            response.setMensaje(user.getNombre());
        } else {
            response.setMensaje("NO LOCALIZADO");
        }
        return new ResponseEntity<ErrorService>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/actualizar/usuario", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> actualizarDatosUsuario(HttpServletRequest request) throws IOException, JSONException, Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;

        String emailUsuario = request.getParameter("emailUsuario");
        String passwordUsuario = request.getParameter("passwordUsuario");

        String nombreUsuario = request.getParameter("nombreUsuario");
        String falta = request.getParameter("falta");
        String fconexion = request.getParameter("fconexion");
        String actividad = request.getParameter("actividad");
        String fnacimiento = request.getParameter("fnacimiento");
        String sexo = request.getParameter("sexo");

        this.log.info("Email Usuario: " + emailUsuario);
        this.log.info("Password Usuario: " + passwordUsuario);

        this.log.info("Nombre Usuario: " + nombreUsuario);
        this.log.info("Fecha Alta Usuario: " + falta);
        this.log.info("Fecha Ultima Conexion Usuario: " + fconexion);
        this.log.info("Actividad Usuario: " + actividad);
        this.log.info("Fecha Nacimiento Usuario: " + fnacimiento);
        this.log.info("Sexo Usuario: " + sexo);

        ErrorService response = new ErrorService();
        response.setCodigo("404");
        response.setMensaje("Los datos del usuario no se pudieron actualizar, su pasword es incorrecto.");

        Usuario user = this.usuarioService.validaEmailSistema(emailUsuario);

        if (user != null) {
            if (!UtilService.Desencriptar(user.getPassword()).equals(passwordUsuario)) {
                return new ResponseEntity<ErrorService>(response, status);
            }
            this.log.info("Usuario encontrado: " + user.toString());
            String encriptarPassword = UtilService.Encriptar(passwordUsuario);
            user.setPassword(encriptarPassword);
            user.setNombre(nombreUsuario);
            this.usuarioService.actualizarDatosUsuario(user);
            this.log.info("El password fue actualizado");
            response.setCodigo("200");
            response.setMensaje("La información fue actualizada con éxito, "
                    + "para continuar tendrá que reingresar al sistema.");
            status = HttpStatus.OK;
        }
        return new ResponseEntity<ErrorService>(response, status);
    }

    public static boolean validaFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fecha Invalida: " + fecha);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/sistema/salir", method = RequestMethod.POST)
    public void exitSistema(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, Exception {
        this.log.info("Registrando salida en sistema.");

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            response.sendRedirect(request.getContextPath());
            return;
        }
        log.info("Sesion activa Token === " + result);
        UserSession usuario = (UserSession) session.getAttribute("usuario");
        Usuario user = this.usuarioService.byIdUser(usuario.getId());
        this.usuarioService.actulizarConexionUsuario(user);
        session.removeAttribute("menu");
        session.removeAttribute("usuario");
        session.removeAttribute("token");
        session.removeAttribute("compras");
        session.removeAttribute("userName");
        session.removeAttribute("publicacionesActivas");
        session.invalidate();
        log.info("Removiendo datos de la session");
        response.sendRedirect(request.getContextPath());
    }
}
