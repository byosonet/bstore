package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Perfil;

/**
 * 
 * @author hustler
 *
 */
public interface PerfilDao {
	Perfil getPerfil(int id);
	List<Perfil> getAll();
}
