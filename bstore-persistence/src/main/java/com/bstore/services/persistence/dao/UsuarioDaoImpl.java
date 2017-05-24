package com.bstore.services.persistence.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.dao.UsuarioDaoImpl;
import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.persistence.utils.HibernateUtil;

public class UsuarioDaoImpl extends HibernateDaoSupport implements UsuarioDao{
    private final Logger log = Logger.getLogger(UsuarioDaoImpl.class);

    @SuppressWarnings("unchecked")
	public List<Usuario> getUser() {
       this.log.info("Buscando por lista de usuarios::");
       return (List<Usuario>) HibernateUtil.getSessionFactory().createQuery("FROM Usuario u " + "ORDER BY u.id ASC").list();
    }

    public Usuario validaUsuario(String email, String password) {
        this.log.info("Buscando usuario by email:: "+email);
        
        
        Usuario usuario = (Usuario) HibernateUtil.getSessionFactory()
                .createQuery("FROM Usuario u WHERE u.email = :email AND u.password = :password AND u.estatus = 1")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
        if(usuario==null){
        	usuario = (Usuario) HibernateUtil.getSessionFactory()
                    .createQuery("FROM Usuario u WHERE u.login = :login AND u.password = :password AND u.estatus = 1")
                    .setParameter("login", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
        return usuario;
    }

    public Usuario validaEmailSistema(String email) {
        this.log.info("Verificando email en BD:: "+email);
        return (Usuario) HibernateUtil.getSessionFactory()
                .createQuery("FROM Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
    
    public void actualizarConexionUsuario(Usuario usuario) {
        Transaction tx = HibernateUtil.beginTransaction(); 
    	try {
            if(usuario!=null){
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("Date::: "+date);
                usuario.setUltConexion(date);
                HibernateUtil.getCurrentSession().saveOrUpdate(usuario);
                HibernateUtil.getCurrentSession().flush();
                tx.commit();
            }
        } catch (HibernateException he) {
            tx.rollback();
            throw he;
        } finally {
        	HibernateUtil.getCurrentSession().close();
        }
    }

    public void agregarUsuario(Usuario usuario) {
    	Transaction tx = HibernateUtil.beginTransaction();
        try {
            usuario.setEstatus(2);
            Perfil perfil = new Perfil();
            perfil.setId(2);
            usuario.setPerfil(perfil);
            HibernateUtil.getCurrentSession().save(usuario);
            HibernateUtil.getCurrentSession().flush();
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            throw he;
        } finally {
        	HibernateUtil.getCurrentSession().close();
        }
    }

    public void actualizarDatosUsuario(Usuario user) {
    	Transaction tx = HibernateUtil.beginTransaction();
        try {
            if(user!=null){
            	HibernateUtil.getCurrentSession().saveOrUpdate(user);
            	HibernateUtil.getCurrentSession().flush();
            	tx.commit();
            }
        } catch (HibernateException he) {
        	tx.rollback();
            throw he;
        } finally {
        	HibernateUtil.closeCurrentSession();
        }
    }

    public void deleteUser(Usuario usuario) {
    	Transaction tx = HibernateUtil.beginTransaction();
        try {
            if(usuario!=null){
            	HibernateUtil.getCurrentSession().delete(usuario);
            	HibernateUtil.getCurrentSession().flush();
                tx.commit();
            }
        } catch (HibernateException he) {
            tx.rollback();
            throw he;
        } finally {
        	HibernateUtil.getCurrentSession().close();
        }
    }

    public Usuario byId(int idUser) {
        this.log.info("Buscando usuario by id:: "+idUser);
        return (Usuario) HibernateUtil.getSessionFactory()
                .createQuery("FROM Usuario u WHERE u.id = :idUser")
                .setParameter("idUser", idUser)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<Usuario> getListaEmailNotificaciones(String notificar) {
        return (List<Usuario>) HibernateUtil.getSessionFactory().createQuery("FROM Usuario u WHERE u.notificaciones = :tipo")
                .setParameter("tipo", notificar)
                .list();
    }

	public Usuario validaLoginSistema(String login) {
		 this.log.info("Verificando login en BD:: "+login);
	        return (Usuario) HibernateUtil.getSessionFactory()
	                .createQuery("FROM Usuario u WHERE u.login = :login")
	                .setParameter("login", login)
	                .uniqueResult();
	}
}
