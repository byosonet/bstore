package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.utils.HibernateUtil;


public class CompraDaoImpl extends HibernateDaoSupport implements CompraDao {
	private final Logger log = Logger.getLogger(CompraDaoImpl.class);


	public Compra getCompra(CompraId compraId) {
		this.log.info("Buscando compra by id:: "+compraId.toString());
        return (Compra) 
        		HibernateUtil.getSessionFactory()
                .createQuery("FROM Compra c WHERE c.id = :compraId")
                .setParameter("compraId", compraId)
                .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Compra> getComprasPorUsuario(int idUsuario) {
		this.log.info("Recuperando lista de compras por idUsuario: "+idUsuario);
		return (List<Compra>) 
				HibernateUtil.getSessionFactory()
				.createQuery("FROM Compra c where c.id.idUsuario = :idUsuario ORDER BY fechaCompra DESC")
				.setParameter("idUsuario", idUsuario)
				
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Compra> getUlrimasComprasPorUsuarioParaMenuMensajes(int idUsuario, int total) {
		this.log.info("Recuperando lista de ultimas compras por idUsuario: "+idUsuario);
		this.log.info("Total a recuperar: "+total);
		return (List<Compra>) HibernateUtil.getSessionFactory()
				.createQuery("FROM Compra c where c.id.idUsuario = :idUsuario ORDER BY fechaCompra DESC")
				.setParameter("idUsuario", idUsuario)
				.setMaxResults(total)
				.list();
	}

	public void generarCompra(Compra compra) {
		Transaction tx = HibernateUtil.beginTransaction();
		 try {
			 HibernateUtil.getCurrentSession().save(compra);
			 HibernateUtil.getCurrentSession().flush();
	         tx.commit();
        } catch (HibernateException he) {
                    he.printStackTrace();;
	            tx.rollback();
	            throw he;
        } finally {
        	HibernateUtil.getCurrentSession().close();
        }
	}
	
}
