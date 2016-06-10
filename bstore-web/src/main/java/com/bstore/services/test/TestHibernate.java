package com.bstore.services.test;

import com.bstore.services.persistence.dap.CompraDao;
import com.bstore.services.persistence.dap.CompraDaoImpl;
/**
 *
 * @author Priscila
 */
import com.bstore.services.persistence.dap.UsuarioDaoImpl;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class TestHibernate {
 
    public static void main(String[] args) throws Exception
    {
    	System.out.println("--Testxx::");
        ApplicationContext context = new FileSystemXmlApplicationContext(""
        		+ "//Users/ulysses/proyectos/refactorBS/refactorBookStore/bstore-web/src/main/java/com/bstore/services/test/applicationContextHibernate.xml");
        
        UsuarioDaoImpl usuario = (UsuarioDaoImpl) context.getBean("usuarioDap");
        System.out.print(" -- Load Usario::");
        
        System.out.println(" -- User: "+usuario.consultar(1).toString());
        
        
        CompraDaoImpl compra = (CompraDaoImpl) context.getBean("compraDap");
        System.out.println(" -- Buscando compra:::");
        
        CompraId id = new CompraId();
        id.setIdPublicacion(1);
        id.setIdUsuario(1);
        Compra c = compra.getCompra(id);
        
        System.out.println(" -- Compra id: "+c.getId());
        System.out.println(" -- Compra id: "+c.getFormaPago().getFormaPago());
        
 
    }
}
