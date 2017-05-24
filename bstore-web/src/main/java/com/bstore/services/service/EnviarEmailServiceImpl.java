package com.bstore.services.service;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Plantilla;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.util.UtilService;

@Component("enviarEmailService")
public class EnviarEmailServiceImpl implements EnviarEmailService{
	
	private final Logger log = Logger.getLogger(EnviarEmailServiceImpl.class);
	
	private final int ID_PLANTILLA_REGISTRO = 1;
	private final int ID_PLANTILLA_PASSWORD = 2;
	private final int ID_PLANTILLA_COMPRA = 3;
	private final int ID_PLANTILLA_BAJA = 4;
	
	private final String BRAND_CARD_VISA = "VISA";
	private final String BRAND_CARD_MC = "MC";
        private final String BRAND_CARD_NA = "NA";
	
	@Autowired
    JavaMailSender mailSender;
	
	@Autowired
	private PlantillaService plantillaService;

	@Override
	@Transactional(readOnly=true)
	public void enviarEmailRegistro(final String toEmail, final String bccEmail, Usuario usuario, String urlConfirmacion)
			throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_REGISTRO);
        final StringWriter swRegistro = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", usuario.getNombre());
        context.put("emailApp", bccEmail);
        context.put("urlToken", urlConfirmacion);
        Velocity.evaluate(
                context,
                swRegistro,
                "velocity-mail-registro",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessageRegistro = swRegistro.toString();
        this.log.info("Merge Template Registro Usuario: " + actualMessageRegistro);

        try {
        	MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(
                            mimeMessage, "UTF-8");
                    messageHelper.setSubject(mail.getSubject());
                    messageHelper.setTo(toEmail);
                    messageHelper.setBcc(bccEmail);
                    messageHelper.setText(actualMessageRegistro, true);
                }
            };
            this.mailSender.send(preparator);
            this.log.info("Correo enviado a: "+toEmail);
        } catch (MailException e) {
            log.error("Correo no pudo ser enviado: "+ e.getMessage());
        }
	}
	
	@Override
	@Transactional(readOnly=true)
	public void enviarEmailBaja(final String toEmail, final String bccEmail, Usuario usuario, String urlConfirmacion)
			throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_BAJA);
        final StringWriter swRegistro = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", usuario.getNombre());
        context.put("emailApp", bccEmail);
        context.put("urlToken", urlConfirmacion);
        Velocity.evaluate(
                context,
                swRegistro,
                "velocity-mail-baja",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessageRegistro = swRegistro.toString();
        this.log.info("Merge Template Baja Usuario: " + actualMessageRegistro);

        try {
        	MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(
                            mimeMessage, "UTF-8");
                    messageHelper.setSubject(mail.getSubject());
                    messageHelper.setTo(toEmail);
                    messageHelper.setBcc(bccEmail);
                    messageHelper.setText(actualMessageRegistro, true);
                }
            };
            this.mailSender.send(preparator);
            this.log.info("Correo enviado a: "+toEmail);
        } catch (MailException e) {
            log.error("Correo no pudo ser enviado: "+ e.getMessage());
        }
	}

	@Override
	@Transactional(readOnly=true)
	public void enviarRecuperacionPassword(final String bccEmail, final Usuario usuario) throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_PASSWORD);
        final StringWriter swPassword = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", usuario.getNombre().toUpperCase());
        context.put("tuclave", UtilService.Desencriptar(usuario.getPassword()));
        context.put("emailApp", bccEmail);
        Velocity.evaluate(
                context,
                swPassword,
                "velocity-mail-password",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessagePassword = swPassword.toString();
        this.log.info("Merge Template Recuperacion Password: " + actualMessagePassword);
        
        try {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, "UTF-8");
                messageHelper.setSubject(mail.getSubject());
                messageHelper.setTo(usuario.getEmail());
                messageHelper.setBcc(bccEmail);
                messageHelper.setText(actualMessagePassword, true);
            }
        };
            this.mailSender.send(preparator);
            this.log.info("Correo enviado a: "+usuario.getEmail());
        } catch (MailException e) {
            log.error("Correo no pudo ser enviado: "+ e.getMessage());
        }
		
	}

	@Override
	@Transactional(readOnly=true)
	public void enviarCompraExitosa(final String toEmail,final String bccEmail, final Usuario usuario, final Compra compra) throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_COMPRA);
        final StringWriter swCompra = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", usuario.getNombre());
        context.put("emailApp", bccEmail);
        context.put("resumenPublicacion", compra.getPublicacion().getResumen()!=null?compra.getPublicacion().getResumen():"");
        context.put("nombrePublicacion", compra.getPublicacion().getNombre());
        context.put("isbnPublicacion", compra.getPublicacion().getIsbn());
        context.put("editorial", compra.getPublicacion().getEditorial().getNombre());
        String dateBuy = new SimpleDateFormat("dd/MM/yyyy").format(compra.getFechaCompra());
        context.put("fechaCompra", dateBuy);
        context.put("numeroTransaccion", compra.getIdConekta());
        context.put("numeroAutorizacion", compra.getAuthCodeCard());
        
        String formaPago = "";
        if(BRAND_CARD_VISA.equalsIgnoreCase(compra.getBrandCard())){
            formaPago = BRAND_CARD_VISA;
        }else if(BRAND_CARD_MC.equalsIgnoreCase(compra.getBrandCard())){
            formaPago = BRAND_CARD_MC;
        }else if(BRAND_CARD_NA.equalsIgnoreCase(compra.getBrandCard())){
            formaPago = BRAND_CARD_NA;
        }
        
        context.put("tipoTarjeta", formaPago);
        
        context.put("ultimoNumeroTarjeta", compra.getLast4Card());
        context.put("total", compra.getPrecioCompra());
        
        Velocity.evaluate(
                context,
                swCompra,
                "velocity-mail-compra",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessageCompra = swCompra.toString();
        this.log.info("Merge Template Compra publicacion: " + actualMessageCompra);

        try {
        	MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(
                            mimeMessage, "UTF-8");
                    messageHelper.setSubject(mail.getSubject());
                    messageHelper.setTo(toEmail);
                    messageHelper.setBcc(bccEmail);
                    messageHelper.setText(actualMessageCompra, true);
                }
            };
            this.mailSender.send(preparator);
            this.log.info("Correo enviado a: "+toEmail);
        } catch (MailException e) {
            log.error("Correo no pudo ser enviado: "+ e.getMessage());
        }
	}

}
