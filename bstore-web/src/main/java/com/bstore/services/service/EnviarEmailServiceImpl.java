package com.bstore.services.service;

import java.io.BufferedReader;
import java.io.StringWriter;

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

import com.bstore.services.persistence.pojo.Plantilla;
import com.bstore.services.persistence.pojo.Usuario;

@Component("enviarEmailService")
public class EnviarEmailServiceImpl implements EnviarEmailService{
	
	private final Logger log = Logger.getLogger(EnviarEmailServiceImpl.class);
	
	private final int ID_PLANTILLA_REGISTRO = 1;
	private final int ID_PLANTILLA_PASSWORD = 2;
	//private final int ID_PLANTILLA_COMPRA = 3;
	
	@Autowired
    JavaMailSender mailSender;
	
	@Autowired
	private PlantillaService plantillaService;

	@Override
	@Transactional(readOnly=true)
	public void enviarEmailRegistro(final String toEmail, final String bccEmail, Usuario usuario)
			throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_REGISTRO);
        final StringWriter swRegistro = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", usuario.getNombre());
        context.put("emailApp", bccEmail);
        Velocity.evaluate(
                context,
                swRegistro,
                "velocity-mail-registro",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessageRegistro = swRegistro.toString();
        this.log.info(" -- Merge Template Registro Usuario: " + actualMessageRegistro);

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
            this.log.info(" -- Correo enviado a: "+toEmail);
        } catch (MailException e) {
            log.error(" -- Correo no pudo ser enviado: "+ e.getMessage());
        }
	}

	@Override
	@Transactional(readOnly=true)
	public void enviarRecuperacionPassword(final String email, final String bccEmail, final Usuario usuario, final String password) throws Exception {
		final Plantilla mail = this.plantillaService.obtenerHTML(ID_PLANTILLA_PASSWORD);
        final StringWriter swPassword = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("tuclave", password);
        Velocity.evaluate(
                context,
                swPassword,
                "velocity-mail-password",
                new BufferedReader(mail.getPlantilla().getCharacterStream()));
        final String actualMessagePassword = swPassword.toString();
        this.log.info(" -- Merge Template Recuperacion Password: " + actualMessagePassword);
        
        try {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, "UTF-8");
                messageHelper.setSubject(mail.getSubject());
                messageHelper.setTo(email);
                messageHelper.setBcc(bccEmail);
                messageHelper.setText(actualMessagePassword, true);
            }
        };
            this.mailSender.send(preparator);
            this.log.info(" -- Correo enviado a: "+email);
        } catch (MailException e) {
            log.error(" -- Correo no pudo ser enviado: "+ e.getMessage());
        }
		
	}

	@Override
	@Transactional(readOnly=true)
	public void enviarCompraExitosa(String emailSistema, String asunto, String nombre, String emailCliente,
			String boydAsunto) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
