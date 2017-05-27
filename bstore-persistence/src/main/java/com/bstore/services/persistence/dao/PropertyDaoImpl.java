package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Properties;
import com.bstore.services.persistence.utils.HibernateUtil;

public class PropertyDaoImpl extends HibernateDaoSupport implements PropertyDao{
	
	private final Logger log = Logger.getLogger(PropertyDaoImpl.class);
	
	public Properties getValueByKey(String key) {
		//this.log.info("Buscando usuario valor de la propiedad by: "+key);
        return (Properties) HibernateUtil.getSessionFactory()
                .createQuery("FROM Properties p WHERE p.key = :key")
                .setParameter("key", key)
                .uniqueResult();
	}

}
