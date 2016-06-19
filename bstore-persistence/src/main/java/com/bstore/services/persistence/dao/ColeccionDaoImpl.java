package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class ColeccionDaoImpl extends HibernateDaoSupport implements ColeccionDao{
	private final Logger log = Logger.getLogger(ColeccionDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();

	@SuppressWarnings("unchecked")
	public List<Coleccion> getColecion(boolean tipoOrden) {
		this.log.info("Buscando colecciones");
		String order="";
		if(tipoOrden){
			order = "desc";
		}else{
			order = "asc";
		}
        return (List<Coleccion>) this
                .getSession()
                .createQuery("FROM Coleccion c WHERE c.estatus = :estatus order by c.ranking desc")
                .setParameter("estatus", 1)
                .list();
	}

}
