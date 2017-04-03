package com.bstore.services.persistence.dao;

import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Anexo;
import com.bstore.services.persistence.utils.HibernateUtil;

public class AnexoDaoImpl extends HibernateDaoSupport implements AnexoDao {

    private final Logger logger = Logger.getLogger(AnexoDaoImpl.class);

    @SuppressWarnings("unchecked")
    public List<Anexo> getAnexosByIdPublicacion(int idPublicacion) {
        this.logger.info("-- Iniciando recuperacion de Anexos para la plantilla numero: " + idPublicacion);

        Session sessionConsulta = HibernateUtil.openConexionResources()
                .openSession();
        List<Anexo> list = (List<Anexo>) sessionConsulta
                .createQuery(
                        "FROM Anexo a WHERE a.idPublicacion = :idPublicacion AND a.activo = :activo order by a.foja asc")
                .setParameter("activo", 1)
                .setParameter("idPublicacion", idPublicacion).list();
        sessionConsulta.close();

        Session sessionActualizacion = HibernateUtil.openConexionResources().openSession();
        boolean imagenEncontrada = false;
        for (Anexo anexo : list) {
            if (anexo.getImagen() == null && anexo.getOrigenImagen() != null && !anexo.getOrigenImagen().isEmpty()) {
                try {
                    Transaction tx = sessionActualizacion.beginTransaction();
                    this.logger.info("Procesando imagen nueva: " + anexo.getOrigenImagen());
                    URL url = new URL(anexo.getOrigenImagen());
                    InputStream is = url.openStream();
                    byte[] imageBytes = IOUtils.toByteArray(is);
                    anexo.setImagen(imageBytes);
                    
                    if(anexo.getOrigenImagenZoom()!=null && !anexo.getOrigenImagenZoom().isEmpty()){
                        URL urlZoom = new URL(anexo.getOrigenImagenZoom());
                        InputStream isZoom = urlZoom.openStream();
                        byte[] imageBytesZoom = IOUtils.toByteArray(isZoom);
                        anexo.setImagenZoom(imageBytesZoom);
                    }else{
                        anexo.setImagenZoom(imageBytes);
                    }
                    
                    Timestamp stamp = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(stamp.getTime());
                    anexo.setFechaAlta(date);
                    anexo.setFechaModificacion(date);
                    anexo.setRecargar(0);

                    sessionActualizacion.saveOrUpdate(anexo);
                    sessionActualizacion.flush();
                    tx.commit();
                    imagenEncontrada = true;
                    this.logger.info("Guardando imagen nueva en BD: " + anexo.getOrigenImagen());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    this.logger.info("No se pudo guardar la imagen en BD: " + anexo.getOrigenImagen());
                }
            } else if (anexo.getImagen() != null && anexo.getOrigenImagen() != null && !anexo.getOrigenImagen().isEmpty()
                    && anexo.getRecargar() == 1) {
                try {
                    Transaction tx = sessionActualizacion.beginTransaction();
                    this.logger.info("Procesando actualizacion de imagen: " + anexo.getOrigenImagen());
                    URL url = new URL(anexo.getOrigenImagen());
                    InputStream is = url.openStream();
                    byte[] imageBytes = IOUtils.toByteArray(is);
                    anexo.setImagen(imageBytes);
                    
                    if(anexo.getOrigenImagenZoom()!=null && !anexo.getOrigenImagenZoom().isEmpty()){
                        URL urlZoom = new URL(anexo.getOrigenImagenZoom());
                        InputStream isZoom = urlZoom.openStream();
                        byte[] imageBytesZoom = IOUtils.toByteArray(isZoom);
                        anexo.setImagenZoom(imageBytesZoom);
                    }else{
                        anexo.setImagenZoom(imageBytes);
                    }

                    Timestamp stamp = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(stamp.getTime());
                    anexo.setFechaModificacion(date);
                    anexo.setRecargar(0);

                    sessionActualizacion.saveOrUpdate(anexo);
                    sessionActualizacion.flush();
                    tx.commit();
                    imagenEncontrada = true;
                    this.logger.info("Guardando actualizacion de imagen en BD: " + anexo.getOrigenImagen());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    this.logger.info("No se pudo realizar la actualizacion de la imagen: " + anexo.getOrigenImagen());
                }
            }
        }
        sessionActualizacion.close();

        if (!imagenEncontrada) {
            return list;
        }

        Session sessionFinal = HibernateUtil.openConexionResources().openSession();
        List<Anexo> listFinal = (List<Anexo>) sessionFinal
                .createQuery("FROM Anexo a WHERE a.idPublicacion = :idPublicacion AND a.activo = :activo order by a.foja asc")
                .setParameter("activo", 1)
                .setParameter("idPublicacion", idPublicacion)
                .list();
        sessionFinal.close();
        return listFinal;
    }

}
