package com.bstore.services.persistence.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.dao.UsuarioDaoImpl;
import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.persistence.pojo.Usuario;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class UsuarioDaoImpl extends HibernateDaoSupport implements UsuarioDao{
    private final Logger log = Logger.getLogger(UsuarioDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();

    @SuppressWarnings("unchecked")
	public List<Usuario> getUser() {
       this.log.info(" -- Buscando por lista de usuarios::");
       return (List<Usuario>) this.getSession().createQuery("FROM Usuario u " + "ORDER BY u.id ASC").list();
    }

    public Usuario validaUsuario(String email, String password) {
        this.log.info(" -- Buscando usuario by email:: "+email);
        
        
        Usuario usuario = (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.email = :email AND u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
        if(usuario==null){
        	usuario = (Usuario) this
                    .getSession()
                    .createQuery("FROM Usuario u WHERE u.login = :login AND u.password = :password")
                    .setParameter("login", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
        return usuario;
    }

    public Usuario validaEmailSistema(String email) {
        this.log.info(" -- Verificando email en BD:: "+email);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
    
    public void actualizarConexionUsuario(Usuario usuario) {
         try {
            this.mysql.iniciarOperacion();
            if(usuario!=null){
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("-- Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("-- Date::: "+date);
                usuario.setUltConexion(date);
                this.mysql.getSesion().saveOrUpdate(usuario);
                this.mysql.getSesion().flush();
                this.mysql.getTx().commit();
            }
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public int agregarUsuario(Usuario usuario) {
        try {
            this.mysql.iniciarOperacion();
            /*Number maximo = (Number) this.getSession().createQuery(
                    "SELECT MAX(u.id) "
                    + "FROM Usuario u")
                    .uniqueResult();

            long id = maximo == null ? 1 : maximo.longValue() + 1;
            usuario.setId(Integer.parseInt(String.valueOf(id)));*/
            usuario.setAPaterno("Dev");
            usuario.setLogin("gtrejo");
            usuario.setEstatus(1);
            Perfil perfil = new Perfil();
            perfil.setId(2);
            usuario.setPerfil(perfil);
            
            this.mysql.getSesion().save(usuario);
            this.mysql.getSesion().flush();

            this.mysql.getTx().commit();
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
        return usuario.getId();
    }

    public void actualizarDatosUsuario(Usuario user) {
        try {
            this.mysql.iniciarOperacion();
            if(user!=null){
                this.mysql.getSesion().saveOrUpdate(user);
                this.mysql.getSesion().flush();
                this.mysql.getTx().commit();
            }
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public void deleteUser(Usuario usuario) {
        try {
            this.mysql.iniciarOperacion();
            if(usuario!=null){
                this.mysql.getSesion().delete(usuario);
                this.mysql.getSesion().flush();
                this.mysql.getTx().commit();
            }
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public Usuario byId(int idUser) {
        this.log.info(" -- Buscando usuario by id:: "+idUser);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.id = :idUser")
                .setParameter("idUser", idUser)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<Usuario> getListaEmailNotificaciones(String notificar) {
        return (List<Usuario>) this.getSession().createQuery("FROM Usuario u WHERE u.notificaciones = :tipo")
                .setParameter("tipo", notificar)
                .list();
    }

	public Usuario validaLoginSistema(String login) {
		 this.log.info(" -- Verificando login en BD:: "+login);
	        return (Usuario) this
	                .getSession()
	                .createQuery("FROM Usuario u WHERE u.login = :login")
	                .setParameter("login", login)
	                .uniqueResult();
	}
}
