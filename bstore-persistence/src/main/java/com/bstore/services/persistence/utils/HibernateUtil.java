package com.bstore.services.persistence.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final SessionFactory sessionFactoryResources;
	
	static {
		try {
			sessionFactory = new Configuration().configure("/com/bstore/services/persistence/config/hibernate.cfg.xml")
					.buildSessionFactory();
			sessionFactoryResources = new Configuration().configure("/com/bstore/services/persistence/config/hibernate_resources.cfg.xml")
					.buildSessionFactory();
		} catch (HibernateException ex) {
			System.err.println("Ocurrió un error en la inicialización de la SessionFactory/sessionFactoryResources: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSessionFactory() {
		return sessionFactory.openSession();
	}
	
	public static Transaction beginTransaction() throws HibernateException{
		 return sessionFactory.getCurrentSession().beginTransaction();
	}
	
	public static Session getCurrentSession() throws HibernateException{
		 return sessionFactory.getCurrentSession();
	}

	public static void closeCurrentSession() {
		sessionFactory.getCurrentSession().close();
	}
	
	public static SessionFactory openConexionResources() {
        return sessionFactoryResources;
	}
}