package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Plantilla;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class PlantillaDaoImpl extends HibernateDaoSupport implements PlantillaDao {
	private final Logger log = Logger.getLogger(PlantillaDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();

	public Plantilla getPlantillaHTMl(int idPlantilla) {
        this.log.info(" -- Obteniendo html para el Id: "+idPlantilla);
        return (Plantilla) this.getSession()
                .createQuery("FROM Plantilla p WHERE p.id = :idPlantilla")
                .setParameter("idPlantilla", idPlantilla)
                .uniqueResult();
	}
}
