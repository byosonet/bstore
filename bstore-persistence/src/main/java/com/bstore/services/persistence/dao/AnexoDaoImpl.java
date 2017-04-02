package com.bstore.services.persistence.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Anexo;
import com.bstore.services.persistence.utils.HibernateUtil;

public class AnexoDaoImpl extends HibernateDaoSupport implements AnexoDao{
	private final Logger logger = Logger.getLogger(AnexoDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Anexo> getAnexosByIdPublicacion(int idPublicacion) {
		this.logger.info("-- Iniciando recuperacion de Anexos para la plantilla numero: "+idPublicacion);
		
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
			if (anexo.getImagen() == null && anexo.getOrigenImagen()!=null && !anexo.getOrigenImagen().isEmpty()) {
				try {
					Transaction tx = sessionActualizacion.beginTransaction();
					this.logger.info("-- Procesando imagen: "+anexo.getOrigenImagen());
					URL url = new URL(anexo.getOrigenImagen());
					InputStream is = url.openStream();
					byte [] availableBytes = new byte [0];
					try {
						byte[] buffer = new byte[8192];
						ByteArrayOutputStream outs = new ByteArrayOutputStream();
						int read = 0;
						while ((read = is.read(buffer)) != -1) {
							outs.write(buffer, 0, read);
						}
						is.close();
						outs.close();
						availableBytes = outs.toByteArray();
						anexo.setImagen(availableBytes);
						sessionActualizacion.saveOrUpdate(anexo);
						sessionActualizacion.flush();
						tx.commit();
						imagenEncontrada = true;
						this.logger.info("-- Actualizacion de imagen correcta: "+anexo.getOrigenImagen());
					} catch (Exception e) {
						e.printStackTrace();
						this.logger.info("-- No se puede almacenar la imagen en BD: "+anexo.getNombreImagen());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					this.logger.info("-- No se pudo realizar la actualizacion de la imagen: "+anexo.getOrigenImagen());
				}
			}
		}
		sessionActualizacion.close();
		
		if(!imagenEncontrada){
			return list;
		}
		
		Session sessionFinal = HibernateUtil.openConexionResources().openSession();
		List<Anexo> listFinal = (List<Anexo>) 
				sessionFinal
				.createQuery("FROM Anexo a WHERE a.idPublicacion = :idPublicacion AND a.activo = :activo order by a.foja asc")
				.setParameter("activo", 1)
				.setParameter("idPublicacion", idPublicacion)
				.list();
		sessionFinal.close();
		return listFinal;
	}

}
