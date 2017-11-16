package com.bstore.services.service;

import com.bstore.services.persistence.pojo.Sesion;

/**
 *
 * @author gtrejo
 */
public interface SesionService {
    Sesion getSesionOnline(String emailUsuario);
    void removeSesionOnline(String idSesion);
    void cretaeSesionOnline(String idSesion, String emailUsuario);
}
