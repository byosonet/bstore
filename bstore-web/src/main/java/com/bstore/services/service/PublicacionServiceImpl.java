package com.bstore.services.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Publicacion;

public class PublicacionServiceImpl implements PublicacionService{
	private final Logger log = Logger.getLogger(PublicacionServiceImpl.class);
	
	@Autowired
	private PublicacionDao publicacionDao;
	
	@Autowired 
	private CompraDao compraDao;

	@Override
	@Transactional
	public List<Publicacion> getPublicacionesByColeccionID(int idColeccion, int idUsuario) {
		
		List<Publicacion> lista = new ArrayList<Publicacion>();
		List<Integer> idPublicacionCompra = new ArrayList<Integer>();
		List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
		if(compras!=null){
			if(compras.size()>0){
				for(Compra c: compras){
					idPublicacionCompra.add(c.getId().getIdPublicacion());
				}
			}
		}
		lista = this.publicacionDao.getPublicaciones(idColeccion);
		if(idPublicacionCompra.size()>0){
			for(Publicacion pub: lista){
				idCompra: for(int id: idPublicacionCompra){
					if(pub.getId() == id){
						pub.setComprada(true);
						break idCompra;
					}
				}
			}
		}
		log.info("Buscando publicaciones por idColeccion: "+idColeccion);
		return lista;
	}

	@Override
	@Transactional
	public Publicacion getPublicacion(int id) {
		return this.publicacionDao.getPublicacion(id);
	}

	@Override
	public List<Publicacion> getAll() {
		log.info("getAll");
		return publicacionDao.getAll();
	}
}
