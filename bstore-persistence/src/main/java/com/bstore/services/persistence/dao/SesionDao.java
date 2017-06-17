package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.pojo.Sesion;

/**
 *
 * @author gtrejo
 */
public interface SesionDao {
    Sesion getSesionOnline(String emailUsuario);
    void removeSesionOnline(String idSession);
    void createSesionOnline(String idSession,String usuario);
}
