package com.bstore.services.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Publicacion;

/**
 * 
 * @author hustler
 *
 */
public class CompraServiceImpl implements CompraService {
	private final Logger log = Logger.getLogger(CompraServiceImpl.class);
	
	@Autowired
	private CompraDao compraDao;
	
	@Autowired
	private PublicacionDao publicacionDao;
	
	@Override
	public Compra getCompra(CompraId compraId) {
		return compraDao.getCompra(compraId);
	}

	@Override
	public List<Compra> listaCompraPorUsuario(int idUsuario) {
		this.log.info("Buscando compras para el idUsuario: "+idUsuario);
		List<Compra> lista = null;
		lista = this.compraDao.getComprasPorUsuario(idUsuario);
		if(lista!=null){
		this.log.info("Total encontrados: "+lista.size());	
		}
		return lista;
	}
	
	@Override
	public Map<Coleccion, List<Publicacion>> getMenuColeccion(List<Compra> compras) {
		Set<Coleccion> colecciones =  new HashSet<Coleccion>();
		for(Compra c: compras){
			Publicacion p = this.publicacionDao.getPublicacion(c.getId().getIdPublicacion());
			if(p!=null){
				colecciones.add(p.getColeccion());
			}
		}
		
		Map<Coleccion, List<Publicacion>> map = new HashMap<Coleccion, List<Publicacion>>();
		for(Coleccion coleccion: colecciones){
			List<Publicacion> publicacion = new ArrayList<Publicacion>();
			for(Compra c: compras){
				Publicacion p = this.publicacionDao.getPublicacion(c.getId().getIdPublicacion());
				if(p!=null){
					if(p.getColeccion().getId() == coleccion.getId()){
						publicacion.add(p);
						map.put(coleccion,publicacion);
					}
				}
			}
		}
		for(Map.Entry<Coleccion, List<Publicacion>> m : map.entrySet()){
			log.info("Coleccion "+m.getKey().getNombreMostrar());
			log.info("Total Coleccion "+m.getValue().size());
			log.info("Coleccion "+m.getValue().toString());
		}
		return map;
	}
	
}
