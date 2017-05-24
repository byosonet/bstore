package com.bstore.services.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Properties;
import com.bstore.services.persistence.pojo.Publicacion;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author gtrejo
 *
 */
public class CompraServiceImpl implements CompraService {

    private final Logger log = Logger.getLogger(CompraServiceImpl.class);

    @Autowired
    private CompraDao compraDao;

    @Autowired
    private PublicacionDao publicacionDao;

    @Autowired
    private PropertyService propertyService;

    @Override
    @Transactional
    public Compra getCompra(CompraId compraId) {
        return compraDao.getCompra(compraId);
    }

    @Override
    @Transactional
    public Map<Coleccion, List<Publicacion>> getMenuColeccion(int idUsuario) {
        this.log.info("Buscando compras para el idUsuario: " + idUsuario);
        List<Compra> lista = null;
        lista = this.compraDao.getComprasPorUsuario(idUsuario);

        Map<Coleccion, List<Publicacion>> map = new HashMap<Coleccion, List<Publicacion>>();
        Map<Coleccion, List<Publicacion>> mapaOrdenado = new TreeMap<Coleccion, List<Publicacion>>(
                new Comparator<Coleccion>() {
            @Override
            public int compare(Coleccion c1, Coleccion c2) {
                return c1.getNombreMostrar().compareTo(c2.getNombreMostrar());
            }

        });
        if (lista != null) {
            this.log.info("Total encontrados: " + lista.size());
            Set<Coleccion> colecciones = new HashSet<Coleccion>();
            for (Compra c : lista) {
                Publicacion p = this.publicacionDao.getPublicacion(c.getId().getIdPublicacion());
                if (p != null) {
                    colecciones.add(p.getColeccion());
                }
            }

            for (Coleccion coleccion : colecciones) {
                List<Publicacion> publicacion = new ArrayList<Publicacion>();
                for (Compra c : lista) {
                    Publicacion p = this.publicacionDao.getPublicacion(c.getId().getIdPublicacion());
                    if (p != null) {
                        if (p.getColeccion().getId() == coleccion.getId()) {
                            publicacion.add(p);
                        }
                    }
                }

                Collections.sort(publicacion, new Comparator<Publicacion>() {
                    @Override
                    public int compare(Publicacion p1, Publicacion p2) {
                        return p1.getNombre().compareTo(p2.getNombre());
                    }
                });

                map.put(coleccion, publicacion);
            }

            mapaOrdenado.putAll(map);

            for (Map.Entry<Coleccion, List<Publicacion>> m : mapaOrdenado.entrySet()) {
                log.info("Nombre de la Coleccion Ordenada: " + m.getKey().getNombreMostrar());
                log.info("Total Publicaciones compradas en la coleccion: " + m.getValue().size());
                log.info("Publicaciones toString: " + m.getValue().toString());
            }
        }
        return mapaOrdenado;
    }

    @Override
    @Transactional
    public List<Publicacion> ultimasCompras(int idUsuario) {
        Properties prop = this.propertyService.getValueKey("max.items.message.buy");
        int totalMostrar = 3;
        if (prop != null) {
            totalMostrar = Integer.valueOf(prop.getValue());
        }

        List<Compra> compras = this.compraDao.getUlrimasComprasPorUsuarioParaMenuMensajes(idUsuario, totalMostrar);
        List<Publicacion> publicacionesCompradas = new ArrayList<Publicacion>();
        for (Compra c : compras) {
            Publicacion pub = this.publicacionDao.getPublicacion(c.getId().getIdPublicacion());
            if (pub != null) {
                pub.setFechaCompraTemporal(c.getFechaCompra());
                publicacionesCompradas.add(pub);
            }
        }
        return publicacionesCompradas;
    }

    @Override
    @Transactional
    public void crearCompra(Compra compra) {
        log.info("Generando y gurdando compra en BD: " + compra.toString());
        this.compraDao.generarCompra(compra);
    }

    @Override
    @Transactional
    public List<Compra> obtenetComprasbyUsuario(int idUsuario) {
        return this.compraDao.getComprasPorUsuario(idUsuario);
    }

}
