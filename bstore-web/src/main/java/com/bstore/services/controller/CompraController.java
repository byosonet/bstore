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
import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.FormaPago;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.FormaPagoService;
import com.bstore.services.service.PublicacionService;


@Controller
public class CompraController {
	private final Logger log = Logger.getLogger(CompraController.class);
	private static final String NAME_CONTROLLER = "[--CompraController--]";
	private final static String TYPE_CARD_VISA = "VISA";
	private final static String TYPE_CARD_MASTERCARD = "MASTERCARD";
	private final static String STATUS_PAID = "paid";
	private final static String TYPE_MONEY_MXN = "MXN";
	private final static String LABEL_ONE = "ISBN: ";
	private final static String LABEL_TWO = " TEMA: ";
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private CompraService compraService;
	
    @Autowired
    @Qualifier("conektaDev")
    private ConektaAdapter conektaAdapter;
    
    @Autowired
    private FormaPagoService formaPagoService;
	
	
	@RequestMapping(value="/comprar/publicacion/{id}",method = RequestMethod.GET)
	   public String getDetalleCompra(Model model, @PathVariable("id") int id, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Cargando detalle de compra: "+NAME_CONTROLLER);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			Publicacion publicacion = this.publicacionService.getPublicacion(id);
			model.addAttribute("publicacion", publicacion);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "detalleCompra";
	   }
	
	@RequestMapping(value="/historial/compras",method = RequestMethod.GET)
	   public String historialCompras(Model model, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.info("Cargando historial de compras: "+NAME_CONTROLLER);
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
			log.info("Procesando historial de compra...");
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			List<Compra> mapaCompras = new ArrayList<Compra>();
			List<Compra> listaCompras = this.compraService.obtenetComprasbyUsuario(usuario.getId());
			if(listaCompras!=null && listaCompras.size()>0){
				for(Compra buy: listaCompras){
					Publicacion pub = this.publicacionService.getPublicacion(buy.getId().getIdPublicacion());
					if(pub!=null){
						log.info("compra id: "+"id: "+buy.getId()+" fecha: "+buy.getFechaCompra());
						log.info("pub id: "+"id: "+pub.getId());
						buy.setPublicacion(pub);
						mapaCompras.add(buy);
					}
				}
			}
			log.info("Total de compras encontradas: "+mapaCompras.size());
			model.addAttribute("mapaCompras", mapaCompras);
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "historialCompra";
	   }
	
	@RequestMapping(value="/pagar/publicacion/{id}",method = RequestMethod.POST)
	   public String pagarPublicacion(Model model, @PathVariable("id") int id, 
			   HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		log.info("Controller::: "+NAME_CONTROLLER);
		log.info("Procesar compra de publicacion con ID::: "+id);
		
		HttpSession session= (HttpSession) request.getSession(false);
		if(session!=null && session instanceof HttpSession && session.getAttribute("token")!=null){
		  Usuario usuario = (Usuario) session.getAttribute("usuario");
		  Publicacion publicacion = this.publicacionService.getPublicacion(id);
		  /*String nombre = request.getParameter("nombre");
		  String numeroTarjeta = request.getParameter("numeroTarjeta");
		  String cvv = request.getParameter("cvv");
		  String fechaExpriacionMes = request.getParameter("fechaExpiracionMes");
		  String fechaExpiracionAnio = request.getParameter("fechaExpiracionAnio");
		  String calle = request.getParameter("calle");
		  String colonia = request.getParameter("colonia");
		  String ciudad = request.getParameter("ciudad");
		  String estado = request.getParameter("estado");
		  String codigoPostal = request.getParameter("codigo");
		  String pais = request.getParameter("pais");*/
		  String tokenConekta = request.getParameter("key");

		  RequestPaymentCard requestCharge = new RequestPaymentCard();
		  	  String price = "";
		  	  BigDecimal priceFinal = new BigDecimal(0);
		  	  if(publicacion.getDescuento().intValue()>0){
		  		  priceFinal = publicacion.getPrecio().subtract(publicacion.getDescuento());
		  	  }else{
		  		  priceFinal = publicacion.getPrecio();
		  	  }
		  	  price = priceFinal.toString().replace(".", "");
			  requestCharge.setAmount(new Long(price));
			  requestCharge.setCurrency(TYPE_MONEY_MXN);
			  requestCharge.setDescription(LABEL_ONE+publicacion.getIsbn());
			  requestCharge.setCard(tokenConekta);
			  requestCharge.setReferenceId("Venta de ISBN: "+"["+publicacion.getIsbn()+"]");
	        
		  	Details details = new Details();
		        details.setName(usuario.getNombre() +" "+usuario.getAPaterno());
		        details.setPhone(usuario.getTelefono()==null?"0000000000":usuario.getTelefono());
		        details.setEmail(usuario.getEmail());
	        
	        Item item = new Item();
		        List<Item> lista = new ArrayList<Item>();
		        item.setName(publicacion.getNombre());
		        item.setPrice(priceFinal.toString());
		        item.setDescription(LABEL_ONE+publicacion.getIsbn()+LABEL_TWO+publicacion.getNombre());
		        lista.add(item);
		        
		        details.setLine_items(lista);
		        requestCharge.setDetails(details);
	        
	        try {
	        	log.info("Request Conekta: "+requestCharge.toString());
	        	ResponsePaymentCard responseCharge =  this.conektaAdapter.createChargeCard(requestCharge);
	            log.info("Response Conekta: "+responseCharge.toString());
	            if(responseCharge.getStatus()!=null && responseCharge.getStatus().equalsIgnoreCase(STATUS_PAID)){
	            	log.info("Mensaje de Conekta: "+responseCharge.getStatus().toUpperCase());
	            	Compra compra = new Compra();
	            	List<FormaPago> listPago = this.formaPagoService.getAll();
	            	for(FormaPago formaPago : listPago){
	            		String tipoTarjeta = responseCharge.getPaymentMethod().getBrand().equalsIgnoreCase("visa")?TYPE_CARD_VISA:TYPE_CARD_MASTERCARD;
	            		if(formaPago.getFormaPago().equalsIgnoreCase(tipoTarjeta)){
	            			log.info("Tipo tarjeta pago: "+formaPago.getFormaPago());
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
	            	
	            	compra.setFechaCompra(new Date());
	            	this.compraService.crearCompra(compra);

	            	model.addAttribute("compra", compra);
	            	model.addAttribute("publicacion", publicacion);
	            	log.info("Compra finalizada con exito: "+compra.toString());
	            	
	      		    Map<Coleccion, List<Publicacion>> menu = this.compraService.getMenuColeccion(usuario.getId());
	          	    model.addAttribute("menu",menu);
	          	    List<Publicacion> ultimasCompras = this.compraService.ultimasCompras(usuario.getId());
	          	    model.addAttribute("ultimasCompras",ultimasCompras);
	          	  
	          	    session.setAttribute("ultimasCompras", ultimasCompras);
	          	    session.setAttribute("menu",menu);
	            }else{
	            	log.info("Mensaje de error conekta 1: "+responseCharge.getError().getError1());
	            	log.info("Mensaje de error conekta 2: "+responseCharge.getError().getError2());
	            	return "pagoFallido";
	            }
	        } catch (Exception e) {
	            log.info("Error con Servicio Externo conekta: "+e.getMessage());
	            e.printStackTrace();
	            return "pagoFallido";
	        } 
		}else{
			response.sendRedirect(request.getContextPath());
		}
		return "pagoCompra";
	   }
	
}
