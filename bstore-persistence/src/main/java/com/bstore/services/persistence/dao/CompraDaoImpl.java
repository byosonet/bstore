package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class CompraDaoImpl extends HibernateDaoSupport implements CompraDao {
	private final Logger log = Logger.getLogger(CompraDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();

	public Compra getCompra(CompraId compraId) {
		this.log.info("Buscando compra by id:: "+compraId.toString());
        return (Compra) this
                .getSession()
                .createQuery("FROM Compra c WHERE c.id = :compraId")
                .setParameter("compraId", compraId)
                .uniqueResult();
	}

}
