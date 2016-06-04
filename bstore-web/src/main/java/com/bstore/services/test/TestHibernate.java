package com.bstore.services.test;

/**
 *
 * @author Priscila
 */
import com.bstore.services.persistence.dao.UsuarioDaoImpl;
import com.bstore.services.persistence.hbm.Usuario;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class TestHibernate {
 
    public static void main(String[] args) throws Exception
    {
    	System.out.println("--Testxx::");
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:com/bstore/services/test/applicationContextHibernate.xml");
        UsuarioDaoImpl usuario = (UsuarioDaoImpl) context.getBean("usuarioDao");
        System.out.print(" -- Load Usario::"+usuario.getUser().size());
        
        for(Usuario u: usuario.getUser()){
        	System.err.println(" -- User: "+u.getNombre()+" email: "+u.getEmail());
        }
 
    }
}
