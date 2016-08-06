package com.bstore.services.service;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Usuario;

public interface EnviarEmailService {
	void enviarEmailRegistro(final String toEmail,final String bccEmail, final Usuario usuario) throws Exception;
	void enviarRecuperacionPassword(final String bccEmail, final Usuario usuario) throws Exception;
	void enviarCompraExitosa(final String toEmail,final String bccEmail, final Usuario usuario, final Compra compra) throws Exception;
}
