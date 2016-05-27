package com.bstore.services.service;

import com.bstore.services.persistence.hbm.PropiedadSistema;

/**
 *
 * @author User
 */
public interface PropiedadSistemaService {
    PropiedadSistema obtenerValorPropiedad (String key);
    void guardarPropiedad(String llave, String valor);
}
