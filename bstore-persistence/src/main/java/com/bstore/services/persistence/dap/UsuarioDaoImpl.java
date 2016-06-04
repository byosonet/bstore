package com.bstore.services.persistence.dap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.dao.TransacctionMySQL;
import com.bstore.services.persistence.dap.UsuarioDaoImpl;
import com.bstore.services.persistence.pojo.Usuario;

public class UsuarioDaoImpl extends HibernateDaoSupport implements UsuarioDao{
	private final Logger log = Logger.getLogger(UsuarioDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    
	public Usuario consultar(int id) {
		this.log.info("Buscando usuario by id:: "+id);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.id = :id")
                .setParameter("id", id)
                .uniqueResult();
	}

	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public void actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void insertar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}
