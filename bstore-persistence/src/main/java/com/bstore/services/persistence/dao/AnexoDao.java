package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Anexo;

public interface AnexoDao {
	List<Anexo> getAnexosByIdPublicacion(int idPublicacion);
}
