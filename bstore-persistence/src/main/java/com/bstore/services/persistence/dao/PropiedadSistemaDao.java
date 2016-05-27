package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.hbm.PropiedadSistema;

/**
 *
 * @author User
 */
public interface PropiedadSistemaDao {
    PropiedadSistema obtenerValorPropiedad(String key);
    void guardarPropiedad (String key, String value);
}
