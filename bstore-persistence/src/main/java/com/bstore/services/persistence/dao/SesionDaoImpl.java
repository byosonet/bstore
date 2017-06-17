package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.pojo.Sesion;
import com.bstore.services.persistence.utils.HibernateUtil;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author gtrejo
 */
public class SesionDaoImpl extends HibernateDaoSupport implements SesionDao{
     private final Logger log = Logger.getLogger(SesionDaoImpl.class);

    @Override
    public Sesion getSesionOnline(String emailUsuario) {
        this.log.info("Buscando sesion para el usuario: " + emailUsuario);
        Sesion sesion = null;
        try {
            sesion = (Sesion) HibernateUtil.getSessionFactory()
                    .createQuery("FROM Sesion s WHERE s.usuario = :emailUsuario")
                    .setParameter("emailUsuario", emailUsuario)
                    .uniqueResult();
        } catch (Exception ex) {
            log.info("Se encontro mas de una session abierta para este usuario: " + emailUsuario);
        }
        return sesion;
    }

    @Override
    public void removeSesionOnline(String idSession) {
        Sesion sesion = getIdSesion(idSession);
        Transaction tx = HibernateUtil.beginTransaction();
        try {
            if (sesion != null) {
                HibernateUtil.getCurrentSession().delete(sesion);
                HibernateUtil.getCurrentSession().flush();
                tx.commit();
                log.info("Se ha borrado la session de la BD: "+idSession);
            }else{
                log.info("La session no ha sido borrada, no existe en BD: "+idSession);
            }
        } catch (HibernateException he) {
            he.printStackTrace();
            tx.rollback();
            throw he;
        } finally {
            HibernateUtil.getCurrentSession().close();
        }
    }
    
    private Sesion getIdSesion(String idSession) {
        this.log.info("Buscando sesion: " + idSession);
        Sesion sesion = null;
        try {
            sesion = (Sesion) HibernateUtil.getSessionFactory()
                    .createQuery("FROM Sesion s WHERE s.idSesion = :idSession")
                    .setParameter("idSession", idSession)
                    .uniqueResult();
        } catch (Exception ex) {
            log.info("No se encontro ninguna sesion con este ID: " + idSession);
        }
        return sesion;
    }

    @Override
    public void createSesionOnline(String idSession, String usuario) {
        Transaction tx = HibernateUtil.beginTransaction();
        Sesion sesion = new Sesion();
        try {
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(stamp.getTime());
            sesion.setIdSesion(idSession);
            sesion.setUsuario(usuario);
            sesion.setFecha(date);
            HibernateUtil.getCurrentSession().saveOrUpdate(sesion);
            HibernateUtil.getCurrentSession().flush();
            tx.commit();
            log.info("Sesion guardada correctamente en la BD: "+idSession);
        } catch (HibernateException he) {
            he.printStackTrace();
            tx.rollback();
            throw he;
        } finally {
            HibernateUtil.getCurrentSession().close();
        }
    }
    
}
