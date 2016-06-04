package com.bstore.services.test;

/**
 *
 * @author Priscila
 */
import com.bstore.services.persistence.dap.UsuarioDaoImpl;

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
 
    }
}
