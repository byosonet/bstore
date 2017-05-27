package com.bstore.services.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.conekta.ConektaAdapter;
import com.bstore.services.conekta.service.RequestPaymentCard;
import com.bstore.services.conekta.service.ResponsePaymentCard;
import com.bstore.services.conekta.service.RequestPaymentCard.Details;
import com.bstore.services.conekta.service.RequestPaymentCard.Details.Item;
import com.bstore.services.model.MenuModel;
import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.FormaPago;
import com.bstore.services.persistence.pojo.Properties;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.EnviarEmailService;
import com.bstore.services.service.FormaPagoService;
import com.bstore.services.service.PropertyService;
import com.bstore.services.service.PublicacionService;
import com.bstore.services.util.UtilService;
import com.bstore.services.util.ValidarSesion;

@Controller
public class CompraController {

    private final Logger log = Logger.getLogger(CompraController.class);
    private static final String NAME_CONTROLLER = "[CompraController]";
    private final static String TYPE_CARD_VISA = "VISA";
    private final static String TYPE_CARD_MASTERCARD = "MASTERCARD";
    private final static String TYPE_CARD_NA = "na";
    private final static String STATUS_PAID = "paid";
    private final static String TYPE_MONEY_MXN = "MXN";
    private final static String LABEL_ONE = "ISBN: ";
    private final static String LABEL_TWO = " TEMA: ";

    private final String VALUE_PERCENTAGE = "com.conekta.porcentaje";
    private final String VALUE_AMOUNT = "com.conekta.cantidad";
    private final String VALUE_TAXE = "com.conekta.iva";
    private final String VALUE_ROUND = "com.conekta.factor.redondeo";
    private final static String NA = "N/A";
    private final String EMAIL_SYSTEM = "com.bstore.mail.app.bcc";
    private final String PAY_CONEKTA = "com.conekta.charge.prod";
    
    private final String USER_EMAIL_SYSTEM = "com.email.system";
    private final String PASSWORD_EMAIL_SYSTEM = "com.password.system";

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private CompraService compraService;

    @Autowired
    @Qualifier("conektaDev")
    private ConektaAdapter conektaAdapterDev;
    
    @Autowired
    @Qualifier("conektaProd")
    private ConektaAdapter conektaAdapterProd;

    @Autowired
    private FormaPagoService formaPagoService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    EnviarEmailService enviarEmailService;

    @RequestMapping(value = "/comprar/publicacion/{id}", method = RequestMethod.GET)
    public String getDetalleCompra(Model model, @PathVariable("id") String id,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Cargando detalle de compra: " + NAME_CONTROLLER);

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        
        int idGenerado = 0;
        try{
            idGenerado = Integer.valueOf(id);
            Publicacion publicacion = this.publicacionService.getPublicacion(idGenerado);
            model.addAttribute("publicacion", publicacion);
            
            if(publicacion==null){
                log.info("Publicacion encontrada: "+publicacion);
                 log.info("Error al procesar: /comprar/publicacion/{id}" + id);
                model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
                return "muestraError";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /comprar/publicacion/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }
        
        return "detalleCompra";
    }

    @RequestMapping(value = "/historial/compras", method = RequestMethod.GET)
    public String historialCompras(Model model,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Cargando historial de compras: " + NAME_CONTROLLER);

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        log.info("Procesando historial de compra...");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Compra> mapaCompras = new ArrayList<Compra>();
        List<Compra> listaCompras = this.compraService.obtenetComprasbyUsuario(usuario.getId());
        if (listaCompras != null && listaCompras.size() > 0) {
            for (Compra buy : listaCompras) {
                Publicacion pub = this.publicacionService.getPublicacion(buy.getId().getIdPublicacion());
                if (pub != null) {
                    log.info("compra id: " + "id: " + buy.getId() + " fecha: " + buy.getFechaCompra());
                    log.info("pub id: " + "id: " + pub.getId());
                    buy.setPublicacion(pub);
                    mapaCompras.add(buy);
                }
            }
        }
        log.info("Total de compras encontradas: " + mapaCompras.size());
        model.addAttribute("mapaCompras", mapaCompras);

        return "historialCompra";
    }

    @RequestMapping(value = "/pagar/publicacion/{id}", method = RequestMethod.POST)
    public String pagarPublicacion(Model model, @PathVariable("id") String id,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("Controller::: " + NAME_CONTROLLER);
        log.info("Procesar compra de publicacion con ID::: " + id);
        
        int idGenerado = 0;
        try{
            idGenerado = Integer.valueOf(id);
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /pagar/publicacion/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Publicacion publicacion = this.publicacionService.getPublicacion(idGenerado);
        String tipoTarj = request.getParameter("visa");
        tipoTarj = tipoTarj != null ? TYPE_CARD_VISA : TYPE_CARD_MASTERCARD;
        String numeroTarjeta = request.getParameter("numeroTarjeta");
        String tokenConekta = request.getParameter("key");

        RequestPaymentCard requestCharge = new RequestPaymentCard();
        String price = "";
        BigDecimal priceFinal = new BigDecimal(0);
        if (publicacion.getDescuento().intValue() > 0) {
            priceFinal = publicacion.getPrecio().subtract(publicacion.getDescuento());
        } else {
            priceFinal = publicacion.getPrecio();
        }
        price = priceFinal.toString().replace(".", "");
        requestCharge.setAmount(new Long(price));
        requestCharge.setCurrency(TYPE_MONEY_MXN);
        requestCharge.setDescription(LABEL_ONE + publicacion.getIsbn());
        requestCharge.setCard(tokenConekta);
        requestCharge.setReferenceId("Venta de ISBN: " + "[" + publicacion.getIsbn() + "]");

        Details details = new Details();
        details.setName(usuario.getNombre() + " " + usuario.getAPaterno());
        try {
            Integer.valueOf(usuario.getTelefono());
            details.setPhone(usuario.getTelefono());
        } catch (Exception ex) {
            ex.printStackTrace();
            details.setPhone("0000000000");
        }
        details.setEmail(usuario.getEmail());

        Item item = new Item();
        List<Item> lista = new ArrayList<Item>();
        item.setName(publicacion.getNombre());
        item.setPrice(priceFinal.toString());
        item.setDescription(LABEL_ONE + publicacion.getIsbn() + LABEL_TWO + publicacion.getNombre());
        lista.add(item);

        details.setLine_items(lista);
        requestCharge.setDetails(details);

        try {
            log.info("Request Conekta: " + requestCharge.toString());
             ResponsePaymentCard responseCharge;
            String typeChargeConekta = this.propertyService.getValueKey(PAY_CONEKTA).getValue();
            if("true".equalsIgnoreCase(typeChargeConekta)){
                log.info("Conekta en Modo: PRODUCTIVO");
                responseCharge = this.conektaAdapterProd.createChargeCard(requestCharge);
            }else{
                log.info("Conekta en Modo: DESARROLLO");
                responseCharge = this.conektaAdapterDev.createChargeCard(requestCharge);
            }
            log.info("Response Conekta: " + responseCharge.toString());
            if (responseCharge.getStatus() != null && responseCharge.getStatus().equalsIgnoreCase(STATUS_PAID)) {
                log.info("Mensaje de Conekta: " + responseCharge.getStatus().toUpperCase());
                Compra compra = new Compra();
                List<FormaPago> listPago = this.formaPagoService.getAll();
                for (FormaPago formaPago : listPago) {
                    String tipoTarjeta = responseCharge.getPaymentMethod().getBrand().equalsIgnoreCase("visa") ? TYPE_CARD_VISA : TYPE_CARD_MASTERCARD;
                    if (formaPago.getFormaPago().equalsIgnoreCase(tipoTarjeta)) {
                        log.info("Tipo tarjeta pago: " + formaPago.getFormaPago());
                        compra.setFormaPago(formaPago);
                        break;
                    }
                }
                compra.setPrecioCompra(priceFinal);
                CompraId idCompra = new CompraId();
                idCompra.setIdPublicacion(publicacion.getId());
                idCompra.setIdUsuario(usuario.getId());
                compra.setId(idCompra);

                //Agregando datos del response de conekta al objeto compra
                compra.setIdConekta(responseCharge.getId());
                compra.setLiveMode(String.valueOf(responseCharge.isLivemode()));
                compra.setStatus(responseCharge.getStatus());
                compra.setCurrencyCard(responseCharge.getCurrency());
                compra.setDescriptionCard(responseCharge.getDescription());
                compra.setNameCard(responseCharge.getPaymentMethod().getName());
                compra.setLast4Card(responseCharge.getPaymentMethod().getLast4());
                compra.setBrandCard(responseCharge.getPaymentMethod().getBrand());
                compra.setAuthCodeCard(responseCharge.getPaymentMethod().getAuthCode());
                compra.setAmountCard(String.valueOf(responseCharge.getAmount()));
                compra.setNameUser(responseCharge.getDetails().getName());
                compra.setPhoneUser(responseCharge.getDetails().getPhone());
                compra.setEmailUser(responseCharge.getDetails().getEmail());

                compra.setPrecioOriginal(this.publicacionService.precioRealPublicacion(idGenerado));
                compra.setDescuentoOriginal(publicacion.getDescuento());
                Properties valuePorcentaje = this.propertyService.getValueKey(VALUE_PERCENTAGE);
                Properties valueCantidad = this.propertyService.getValueKey(VALUE_AMOUNT);
                Properties valueIva = this.propertyService.getValueKey(VALUE_TAXE);
                if (valuePorcentaje != null && valueCantidad != null && valueIva != null) {
                    log.info("Guardando las propiedades de comision de Conekta:::");
                    compra.setConektaComisionPorcentaje(this.propertyService.getValueKey(VALUE_PERCENTAGE).getValue());
                    compra.setConektaComisionCantidad(this.propertyService.getValueKey(VALUE_AMOUNT).getValue());
                    compra.setConektaComisionIva(this.propertyService.getValueKey(VALUE_TAXE).getValue());
                    compra.setFactorRedondeo(this.propertyService.getValueKey(VALUE_ROUND).getValue());
                } else {
                    this.log.info("No se guarda ninguna comision de las propiedades, no han sido definidas en sistemas:::");
                    compra.setConektaComisionPorcentaje(NA);
                    compra.setConektaComisionCantidad(NA);
                    compra.setConektaComisionIva(NA);
                    compra.setFactorRedondeo(NA);
                }
                compra.setFechaCompra(new Date());
                this.compraService.crearCompra(compra);

                model.addAttribute("compra", compra);
                model.addAttribute("publicacion", publicacion);
                log.info("Compra finalizada con exito: " + compra.toString());

                List<MenuModel> menu = this.compraService.getMenuColeccion(usuario.getId());
                model.addAttribute("menu", menu);
                List<Publicacion> ultimasCompras = this.compraService.ultimasCompras(usuario.getId());
                model.addAttribute("ultimasCompras", ultimasCompras);

                session.setAttribute("ultimasCompras", ultimasCompras);
                session.setAttribute("menu", menu);

                try {
                    compra.setPublicacion(publicacion);
                    this.enviarEmailService.enviarCompraExitosa(usuario.getEmail(), 
                            this.propertyService.getValueKey(EMAIL_SYSTEM).getValue().split(";"), usuario, compra,
                            this.propertyService.getValueKey(USER_EMAIL_SYSTEM).getValue(),
                            this.propertyService.getValueKey(PASSWORD_EMAIL_SYSTEM).getValue());
                    this.log.info("Enviado mail de compra exitosa");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    this.log.error("No se puedo enviar mail de compa: " + ex.getMessage());
                }

            } else {
                log.info("Mensaje de error conekta 1: " + responseCharge.getError().getError1());
                log.info("Mensaje de error conekta 2: " + responseCharge.getError().getError2());
                model.addAttribute("errorMessage", true);
                model.addAttribute("messageError", "Error al procesar la Tarjeta [" + tipoTarj + "**** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4) + "]");
                model.addAttribute("messageErrorConekta", responseCharge.getError().getError1());
                model.addAttribute("publicacion", publicacion);
                return "detalleCompra";
            }
        } catch (Exception e) {
            log.info("Error con Servicio Externo conekta: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", true);
            model.addAttribute("messageError", "Por el momento el servicio para procesar pagos no está disponible, intente más tarde.");
            model.addAttribute("messageErrorConekta", e.getMessage());
            model.addAttribute("publicacion", publicacion);
            return "detalleCompra";
        }
        return "pagoCompra";
    }

    @RequestMapping(value = "/pagar/publicacion/gratis/{id}", method = RequestMethod.POST)
    public String pagarPublicacionGratis(Model model, @PathVariable("id") String id,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("Controller::: " + NAME_CONTROLLER);
        log.info("Procesar compra de publicacion con ID::: " + id);
        
        int idGenerado = 0;
        try{
            idGenerado = Integer.valueOf(id);
        }catch(Exception ex){
            ex.printStackTrace();
            log.info("Error al procesar: /pagar/publicacion/gratis/{id}" + id);
            model.addAttribute("mensajeError", "Lo sentimos el identificador enviado es inválido ["+id+"]");
            return "muestraError";
        }

        HttpSession session = (HttpSession) request.getSession(false);
        String result = ValidarSesion.validarSesionUsuarioActual(session);
        if (result.equalsIgnoreCase(ValidarSesion.FORBIDDEN)) {
            log.info(ValidarSesion.MSG_FORBIDDEN);
            return "forbidden";
        }
        log.info("Sesion activa Token === " + result);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Publicacion publicacion = this.publicacionService.getPublicacion(idGenerado);
        
        if(publicacion==null){
            log.info("Estan intentando procesar una compra con una publicacion invalida");
            model.addAttribute("errorMessage", true);
            model.addAttribute("messageError", "No se puede procesar está publicación, pongase en contacto con el Administrador.");
            model.addAttribute("messageErrorConekta", "Publicación Inválida");
            model.addAttribute("publicacion", publicacion);
            return "detalleCompra";
        }else if(publicacion.getPrecio()!=null && publicacion.getPrecio().intValue() > 0){
            log.info("Estan intentando procesar una compra con una publicacion invalida");
            model.addAttribute("errorMessage", true);
            model.addAttribute("messageError", "No se puede procesar está publicación, pongase en contacto con el Administrador.");
            model.addAttribute("messageErrorConekta", "Publicación con precio > $0.00");
            model.addAttribute("publicacion", publicacion);
            return "detalleCompra";
        }

        String tipoTarjeta = TYPE_CARD_NA;

        try {
            Compra compra = new Compra();
            List<FormaPago> listPago = this.formaPagoService.getAll();
            for (FormaPago formaPago : listPago) {
                if (formaPago.getFormaPago().equalsIgnoreCase(tipoTarjeta)) {
                    log.info("Tipo tarjeta pago: " + formaPago.getFormaPago());
                    compra.setFormaPago(formaPago);
                    break;
                }
            }
            compra.setPrecioCompra(publicacion.getPrecio());
            CompraId idCompra = new CompraId();
            idCompra.setIdPublicacion(publicacion.getId());
            idCompra.setIdUsuario(usuario.getId());
            compra.setId(idCompra);
            
            String md5 = UtilService.generateTokenMD5();            
            compra.setIdConekta(md5);
            compra.setLiveMode("local");
            compra.setStatus("paid");
            compra.setCurrencyCard("MXN");
            compra.setDescriptionCard("na");
            compra.setNameCard("na");
            compra.setLast4Card("na");
            compra.setBrandCard(tipoTarjeta);
            compra.setAuthCodeCard("na");
            compra.setAmountCard("000");
            compra.setNameUser(usuario.getNombre());
            compra.setPhoneUser(usuario.getTelefono()!= null ? usuario.getTelefono():"NA");
            compra.setEmailUser(usuario.getEmail());

            compra.setPrecioOriginal(this.publicacionService.precioRealPublicacion(idGenerado));
            compra.setDescuentoOriginal(publicacion.getDescuento());
            Properties valuePorcentaje = this.propertyService.getValueKey(VALUE_PERCENTAGE);
            Properties valueCantidad = this.propertyService.getValueKey(VALUE_AMOUNT);
            Properties valueIva = this.propertyService.getValueKey(VALUE_TAXE);
            if (valuePorcentaje != null && valueCantidad != null && valueIva != null) {
                log.info("Guardando las propiedades de comision de Conekta:::");
                compra.setConektaComisionPorcentaje(this.propertyService.getValueKey(VALUE_PERCENTAGE).getValue());
                compra.setConektaComisionCantidad(this.propertyService.getValueKey(VALUE_AMOUNT).getValue());
                compra.setConektaComisionIva(this.propertyService.getValueKey(VALUE_TAXE).getValue());
                compra.setFactorRedondeo(this.propertyService.getValueKey(VALUE_ROUND).getValue());
            } else {
                this.log.info("No se guarda ninguna comision de las propiedades, no han sido definidas en sistemas:::");
                compra.setConektaComisionPorcentaje(NA);
                compra.setConektaComisionCantidad(NA);
                compra.setConektaComisionIva(NA);
                compra.setFactorRedondeo(NA);
            }
            compra.setFechaCompra(new Date());
            this.compraService.crearCompra(compra);

            model.addAttribute("compra", compra);
            model.addAttribute("publicacion", publicacion);
            log.info("Compra finalizada con exito: " + compra.toString());

            List<MenuModel> menu = this.compraService.getMenuColeccion(usuario.getId());
            model.addAttribute("menu", menu);
            List<Publicacion> ultimasCompras = this.compraService.ultimasCompras(usuario.getId());
            model.addAttribute("ultimasCompras", ultimasCompras);

            session.setAttribute("ultimasCompras", ultimasCompras);
            session.setAttribute("menu", menu);

            try {
                compra.setPublicacion(publicacion);
                this.enviarEmailService.enviarCompraExitosa(usuario.getEmail(), this.propertyService.getValueKey(EMAIL_SYSTEM).getValue().split(";"), usuario, compra,
                        this.propertyService.getValueKey(USER_EMAIL_SYSTEM).getValue(),
                        this.propertyService.getValueKey(PASSWORD_EMAIL_SYSTEM).getValue());
                this.log.info("Enviado mail de compra exitosa");
            } catch (Exception ex) {
                ex.printStackTrace();
                this.log.error("No se puedo enviar mail de compa: " + ex.getMessage());
            }

        } catch (Exception e) {
            log.info("No se puede procesar la compra Gratis: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", true);
            model.addAttribute("messageError", "Por el momento el servicio para procesar pagos no está disponible, intente más tarde.");
            model.addAttribute("messageErrorConekta", e.getMessage());
            model.addAttribute("publicacion", publicacion);
            return "detalleCompra";
        }
        return "pagoCompra";
    }
}
