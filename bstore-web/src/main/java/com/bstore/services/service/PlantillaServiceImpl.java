package com.bstore.services.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.dao.PlantillaDao;
import com.bstore.services.persistence.pojo.Plantilla;

public class PlantillaServiceImpl implements PlantillaService {
	private Logger logger = Logger.getLogger(PlantillaServiceImpl.class);
	
	@Autowired
	private PlantillaDao plantillaDao;

	@Override
	@Transactional(readOnly=true)
	public Plantilla obtenerHTML(int idPlantilla) {
		this.logger.info("Buscando plantilla HTML: "+idPlantilla);
		Plantilla plantilla = null;
		plantilla = this.plantillaDao.getPlantillaHTMl(idPlantilla);
		if(plantilla!=null){
			try {
				this.logger.info("Mail Obtenido: "+plantilla.convertClobToString(plantilla.getPlantilla()));
			} catch (Exception e) {
				this.logger.error("Error: "+e.getMessage());
				e.printStackTrace();
			}
		}
		return plantilla;
	}

}
