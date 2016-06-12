package com.bstore.services.test;

import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.dao.CompraDaoImpl;
import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.dao.PublicacionDaoImpl;
import com.bstore.services.persistence.dao.UsuarioDaoImpl;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.persistence.pojo.Usuario;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class TestHibernate {
 
    public static void main(String[] args) throws Exception
    {
    	System.out.println("--Testxx::");
        ApplicationContext context = new FileSystemXmlApplicationContext(""
        		+ "//Users/ulysses/proyectos/refactorBS/refactorBookStore/bstore-web/src/main/java/com/bstore/services/test/applicationContextHibernate.xml");
        
        UsuarioDaoImpl usuario = (UsuarioDaoImpl) context.getBean("usuarioDao");
        System.out.print(" -- Load Usario::");
        
        System.out.println(" -- User: "+usuario.byId(1).toString());
        
        
        CompraDaoImpl compra = (CompraDaoImpl) context.getBean("compraDao");
        System.out.println(" -- Buscando compra:::");
        
        CompraId id = new CompraId();
        id.setIdPublicacion(1);
        id.setIdUsuario(1);
        
        Compra c = compra.getCompra(id);
        
        System.out.println(" -- Compra id: "+c.getId());
        System.out.println(" -- Compra id: "+c.getFormaPago().getFormaPago());
        
        
        PublicacionDao publicacion = (PublicacionDaoImpl) context.getBean("publicacionDao");
        System.out.println(" -- Buscando Publicacion:::");
        Publicacion p = publicacion.getPublicacion(1);
        
        System.out.println(" -- Publicacion: "+p.getNombre());
        System.out.println(" -- Fuente: "+p.getFuente().getNombreBiblioteca());
        System.out.println(" -- Editorial: "+p.getEditorial().getNombre());
        System.out.println(" -- Coleccion: "+p.getColeccion().getNombre());
        System.out.println(" -- Usuario creo: "+p.getUsuario().getNombre());
    }
}
