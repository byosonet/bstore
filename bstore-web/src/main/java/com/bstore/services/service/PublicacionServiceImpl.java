package com.bstore.services.service;

import com.bstore.services.model.PublicacionActiva;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.dao.AnexoDao;
import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.dao.PropertyDao;
import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.pojo.Anexo;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Properties;
import com.bstore.services.persistence.pojo.Publicacion;

public class PublicacionServiceImpl implements PublicacionService {

    private final Logger log = Logger.getLogger(PublicacionServiceImpl.class);

    private final String VALUE_PERCENTAGE = "com.conekta.porcentaje";
    private final String VALUE_AMOUNT = "com.conekta.cantidad";
    private final String VALUE_TAXE = "com.conekta.iva";
    private final String VALUE_ROUND = "com.conekta.factor.redondeo";
    private final int VALUE_100 = 100;
    ;
	private final int REDONDEO_DECIMALES = 3;

    @Autowired
    private PublicacionDao publicacionDao;

    @Autowired
    private CompraDao compraDao;

    @Autowired
    private PropertyDao propertyDao;

    @Autowired
    private AnexoDao anexoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesByColeccionID(int idColeccion, int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicaciones(idColeccion);
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);

                        /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }

                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones por idColeccion: " + idColeccion);
        if (lista != null && lista.size() > 0) {
            this.ordenarPublicaciones(lista);
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesActivas(int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesActivas();
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones Activas por usuario...IdUser ==== " + idUsuario);
        if (lista != null && lista.size() > 0) {
            this.ordenarPublicaciones(lista);
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PublicacionActiva> getPublicacionesActivasModel(int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesActivas();
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones Activas por usuario...IdUser ==== " + idUsuario);
        if (lista != null && lista.size() > 0) {
            this.ordenarPublicaciones(lista);
        }
        
        List<PublicacionActiva> modelLista = new ArrayList<PublicacionActiva>();
        if(lista!=null && lista.size()>0){
            for(Publicacion pub: lista){
                PublicacionActiva activa = new PublicacionActiva();
                activa.setId(pub.getId());
                activa.setName(pub.getNombre());
                activa.setPurchased(pub.isComprada());
                modelLista.add(activa);
            }
        }
        return modelLista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesByNombreAsc(int idColeccion, int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesPorNombreAsc(idColeccion);
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones por idColeccion: " + idColeccion);
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesByNombreDesc(int idColeccion, int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesPorNombreDesc(idColeccion);
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones por idColeccion: " + idColeccion);
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesByPrecioAsc(int idColeccion, int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesPorPrecioAsc(idColeccion);
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones por idColeccion: " + idColeccion);
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getPublicacionesByPrecioDesc(int idColeccion, int idUsuario) {

        List<Publicacion> lista = new ArrayList<Publicacion>();
        List<Integer> idPublicacionCompra = new ArrayList<Integer>();
        List<Compra> compras = this.compraDao.getComprasPorUsuario(idUsuario);
        if (compras != null) {
            if (compras.size() > 0) {
                for (Compra c : compras) {
                    idPublicacionCompra.add(c.getId().getIdPublicacion());
                }
            }
        }
        lista = this.publicacionDao.getPublicacionesPorPrecioDesc(idColeccion);
        if (idPublicacionCompra.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
                idCompra:
                for (int id : idPublicacionCompra) {
                    if (pub.getId() == id) {
                        pub.setComprada(true);
                        
                         /**
                         * Buscando fecha de compra
                         */
                        CompraId idCompra = new CompraId();
                        idCompra.setIdUsuario(idUsuario);
                        idCompra.setIdPublicacion(pub.getId());
                        Compra comp = this.compraDao.getCompra(idCompra);
                        if (comp != null) {
                            pub.setFechaCompraTemporal(comp.getFechaCompra());
                        } else {
                            pub.setFechaCompraTemporal(null);
                        }
                        
                        break idCompra;
                    }
                }
            }
        } else {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        log.info("Buscando publicaciones por idColeccion: " + idColeccion);
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Publicacion getPublicacion(int id) {
        Publicacion pub = this.publicacionDao.getPublicacion(id);
        if (pub != null) {
            pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
        }
        return pub;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> getAll() {
        log.info("getAll");
        List<Publicacion> lista = publicacionDao.getAll();
        if (lista != null && lista.size() > 0) {
            for (Publicacion pub : lista) {
                pub.setPrecio(this.calculatePriceWithComissionConekta(pub.getPrecio()));
            }
        }
        return lista;
    }

    @Override
    @Transactional
    public BigDecimal precioRealPublicacion(int idPublicacion) {
        Publicacion pub = this.publicacionDao.getPublicacion(idPublicacion);
        log.info("Retornando precio original de la publicacion: " + pub.getPrecio());
        return pub.getPrecio();
    }

    private BigDecimal calculatePriceWithComissionConekta(BigDecimal price) {
        Properties valuePorcentaje = this.propertyDao.getValueByKey(VALUE_PERCENTAGE);
        Properties valueCantidad = this.propertyDao.getValueByKey(VALUE_AMOUNT);
        Properties valueIva = this.propertyDao.getValueByKey(VALUE_TAXE);
        Properties valueRedondeo = this.propertyDao.getValueByKey(VALUE_ROUND);
        if (valuePorcentaje != null && valueCantidad != null && valueIva != null && valueRedondeo != null && price != null && price.intValue() > 0) {
            //this.log.info("Price recibido para calcular:::"+price);
            BigDecimal nPrice = BigDecimal.ZERO;
            BigDecimal fporcentaje = new BigDecimal(this.propertyDao.getValueByKey(VALUE_PERCENTAGE).getValue());
            fporcentaje = fporcentaje.divide(new BigDecimal(VALUE_100), REDONDEO_DECIMALES, BigDecimal.ROUND_HALF_UP);
            BigDecimal fcantidad = new BigDecimal(this.propertyDao.getValueByKey(VALUE_AMOUNT).getValue());
            BigDecimal fiva = new BigDecimal(this.propertyDao.getValueByKey(VALUE_TAXE).getValue());
            BigDecimal fredondeo = new BigDecimal(this.propertyDao.getValueByKey(VALUE_ROUND).getValue());
            fiva = fiva.divide(new BigDecimal(VALUE_100), REDONDEO_DECIMALES, BigDecimal.ROUND_HALF_UP);
            //this.log.info("Factor porcentaje para conekta es de: "+fporcentaje);
            //this.log.info("Factor cantidad para conekta es de: "+fcantidad);
            //this.log.info("Factor iva para conekta es de: "+fiva);
            this.log.info("Ejecutando operaciones...");
            nPrice = price.multiply(fporcentaje).add(fcantidad);
            //this.log.info("Primera operacion: "+nPrice);
            nPrice = nPrice.add(nPrice.multiply(fiva));
            //this.log.info("Segunda operacion: "+nPrice);
            nPrice = nPrice.add(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            //this.log.info("Tercera operacion precio de Conekta: "+nPrice);
            nPrice = nPrice.add(fredondeo);
            this.log.info("Operacion Final precio de Conekta + factor de Redondeo::: " + nPrice);
            return nPrice;
        } else {
            this.log.info("No se aplica ninguna comision, las propiedades no han sido definidas en sistema o la publicacion tiene precio -> $ 0.00 <-");
            return price;
        }
    }

    @Override
    public void saveOrUpdate(Publicacion publicacion) {
        log.info("saveOrUpdate publicacion: " + publicacion.toString());
        publicacionDao.saveOrUpdate(publicacion);
    }

    @Override
    public List<Publicacion> search(Publicacion publicacion) {
        log.info("search: " + publicacion.toString());
        return publicacionDao.search(publicacion);
    }

    @Override
    @Transactional
    public List<Anexo> buscarAnexos(int idPublicacion) {
        List<Anexo> list = new ArrayList<Anexo>();
        list = this.anexoDao.getAnexosByIdPublicacion(idPublicacion);
        if (list != null) {
            this.log.info("Anexos encontrados para la idPublicacion: " + idPublicacion);
            this.log.info("Anexos: " + list.toString());
        } else {
            this.log.info("No se encontraon anexos para la publicacion: " + idPublicacion);
        }
        return list;
    }

    @SuppressWarnings("unused")
    private void ordenarPublicaciones(List<Publicacion> lista) {
        Collections.sort(lista, new Comparator<Publicacion>() {
            @Override
            public int compare(final Publicacion object1, final Publicacion object2) {
                return object1.getNombre().compareTo(object2.getNombre());
            }
        });
    }

}
