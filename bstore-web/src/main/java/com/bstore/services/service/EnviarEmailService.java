package com.bstore.services.service;

import com.bstore.services.persistence.pojo.Usuario;

public interface EnviarEmailService {
	void enviarEmailRegistro(final String toEmail,final String bccEmail, final Usuario usuario) throws Exception;
	void enviarRecuperacionPassword(final String email, final String bccEmail, final Usuario usuario, final String password) throws Exception;
	void enviarCompraExitosa(final String emailSistema, final String asunto, final String nombre, final String emailCliente, final String boydAsunto) throws Exception;
}
