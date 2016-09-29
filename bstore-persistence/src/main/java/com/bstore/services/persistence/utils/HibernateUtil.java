package com.bstore.services.persistence.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure("/com/bstore/services/persistence/config/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
			throw new ExceptionInInitializerError(he);
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
}