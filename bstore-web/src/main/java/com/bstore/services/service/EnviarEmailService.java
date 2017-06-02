package com.bstore.services.service;

import com.bstore.services.model.UserSession;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Usuario;

public interface EnviarEmailService {

    void enviarEmailRegistro(final String toEmail, 
            final String[] bccEmail, 
            final String usuario, 
            final String urlConfirmacion, 
            final String userSystem, 
            final String passwordSystem) throws Exception;

    void enviarEmailBaja(final String toEmail, 
            final String[] bccEmail, 
            final UserSession usuario, 
            final String urlConfirmacion, 
            final String userSystem, 
            final String passwordSystem) throws Exception;

    void enviarRecuperacionPassword(final String[] bccEmail, 
            final Usuario usuario, 
            final String userSystem, 
            final String passwordSystem) throws Exception;

    void enviarCompraExitosa(final String toEmail, 
            final String[] bccEmail, 
            final UserSession usuario, 
            final Compra compra, 
            final String userSystem, 
            final String passwordSystem) throws Exception;
}
