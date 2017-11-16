package com.bstore.services.service;

import java.util.List;

import com.bstore.services.persistence.pojo.Perfil;

/**
 * 
 * @author hustler
 *
 */
public interface PerfilService {
	Perfil getPerfil(int id);
	List<Perfil> getAll();
	void saveOrUpdate(Perfil perfil);
}
