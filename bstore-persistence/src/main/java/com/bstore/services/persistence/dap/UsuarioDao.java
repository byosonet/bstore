package com.bstore.services.persistence.dap;

import java.util.List;

import com.bstore.services.persistence.pojo.Usuario;

public interface UsuarioDao {
	Usuario consultar(int id);
	List<Usuario> listar();;
	void actualizar(Usuario usuario);
	void eliminar(Usuario usuario);
	void insertar(Usuario usuario);
}
