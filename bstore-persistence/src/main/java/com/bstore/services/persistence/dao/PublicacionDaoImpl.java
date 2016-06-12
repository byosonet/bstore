package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class PublicacionDaoImpl extends HibernateDaoSupport implements PublicacionDao{
	private final Logger log = Logger.getLogger(PublicacionDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
	public Publicacion getPublicacion(int idPublicacion) {
		this.log.info("Buscando Publicacion by id:: "+idPublicacion);
        return (Publicacion) this
                .getSession()
                .createQuery("FROM Publicacion p WHERE p.id = :idPublicacion")
                .setParameter("idPublicacion", idPublicacion)
                .uniqueResult();
		}

}
