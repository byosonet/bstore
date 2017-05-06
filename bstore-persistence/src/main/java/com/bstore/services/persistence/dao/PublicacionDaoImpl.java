package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.utils.HibernateUtil;

public class PublicacionDaoImpl extends HibernateDaoSupport implements PublicacionDao{
	private final Logger log = Logger.getLogger(PublicacionDaoImpl.class);
	public Publicacion getPublicacion(int idPublicacion) {
        return (Publicacion) HibernateUtil.getSessionFactory() 
                .createQuery("FROM Publicacion p WHERE p.id = :idPublicacion and p.estatus = :estatus")
                .setParameter("idPublicacion", idPublicacion)
                .setParameter("estatus", 1)
                .uniqueResult();
		}
	
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicaciones(int idColeccion) {
		this.log.info("Buscando Publicacion by coleccion id:: "+idColeccion);
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.coleccion.id = :idColeccion and p.estatus = :estatus")
                .setParameter("idColeccion", idColeccion)
                .setParameter("estatus", 1)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicacionesActivas() {
		this.log.info("Buscando Publicaciones Activas..");
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.estatus = :estatus")
                .setParameter("estatus", 1)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicacionesPorNombreAsc(int idColeccion) {
		this.log.info("Buscando Publicacion by coleccion id:: "+idColeccion);
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.coleccion.id = :idColeccion and p.estatus = :estatus order by p.nombre ASC")
                .setParameter("idColeccion", idColeccion)
                .setParameter("estatus", 1)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicacionesPorNombreDesc(int idColeccion) {
		this.log.info("Buscando Publicacion by coleccion id:: "+idColeccion);
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.coleccion.id = :idColeccion and p.estatus = :estatus order by p.nombre DESC")
                .setParameter("idColeccion", idColeccion)
                .setParameter("estatus", 1)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicacionesPorPrecioAsc(int idColeccion) {
		this.log.info("Buscando Publicacion by coleccion id:: "+idColeccion);
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.coleccion.id = :idColeccion and p.estatus = :estatus order by p.precio ASC")
                .setParameter("idColeccion", idColeccion)
                .setParameter("estatus", 1)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publicacion> getPublicacionesPorPrecioDesc(int idColeccion) {
		this.log.info("Buscando Publicacion by coleccion id:: "+idColeccion);
        return (List<Publicacion>) HibernateUtil.getSessionFactory()
                .createQuery("FROM Publicacion p WHERE p.coleccion.id = :idColeccion and p.estatus = :estatus order by p.precio DESC")
                .setParameter("idColeccion", idColeccion)
                .setParameter("estatus", 1)
                .list();
	}

	@SuppressWarnings("unchecked")
	public List<Publicacion> getAll() {
		logger.info("getAll");
		return (List<Publicacion>) HibernateUtil.getSessionFactory().createQuery("FROM Publicacion p").list();
	}


	public void saveOrUpdate(Publicacion publicacion) {
		logger.info("saveOrUpdate publicacion: "+publicacion.toString());
		HibernateUtil.getSessionFactory().saveOrUpdate(publicacion);
		HibernateUtil.getSessionFactory().flush();
	}

	@SuppressWarnings("unchecked")
	public List<Publicacion> search(Publicacion publicacion) {
		logger.info("search");
		return (List<Publicacion>) HibernateUtil.getSessionFactory()
				.createQuery("FROM Publicacion p WHERE p.coleccion.resumen like :resumen or p.coleccion.nombre like :nombre")
				.setParameter("resumen", publicacion.getResumen())
				.setParameter("nombre", publicacion.getNombre())
				.list();
	}
}
