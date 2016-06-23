package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Perfil;

/**
 * 
 * @author hustler
 *
 */
public class PerfilDaoImpl extends HibernateDaoSupport implements PerfilDao {

	Logger logger = Logger.getLogger(PerfilDaoImpl.class);
	
	public Perfil getPerfil(int id) {
		logger.info("Buscando perfil por id: "+id);
		
		return (Perfil) this
                .getSession()
                .createQuery("FROM Perfil p WHERE p.id = :id")
                .setParameter("id", id)
                .uniqueResult();
	}

}
