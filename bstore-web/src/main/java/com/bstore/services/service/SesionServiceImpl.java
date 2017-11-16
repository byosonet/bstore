package com.bstore.services.service;

import com.bstore.services.persistence.dao.SesionDao;
import com.bstore.services.persistence.pojo.Sesion;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gtrejo
 */
public class SesionServiceImpl implements SesionService{
    private final Logger log = Logger.getLogger(SesionServiceImpl.class);
    
    @Autowired
    private SesionDao sesionDao;

    @Transactional
    public Sesion getSesionOnline(String emailUsuario) {
        Sesion sesion = this.sesionDao.getSesionOnline(emailUsuario);
        return sesion;
    }

    @Transactional
    public void removeSesionOnline(String idSesion) {
        this.sesionDao.removeSesionOnline(idSesion);
    }

    @Transactional
    public void cretaeSesionOnline(String idSesion, String emailUsuario) {
       this.sesionDao.createSesionOnline(idSesion, emailUsuario);
    }
    
}
