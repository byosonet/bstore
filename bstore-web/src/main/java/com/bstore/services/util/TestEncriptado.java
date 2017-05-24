package com.bstore.services.util;

/**
 *
 * @author gtrejo
 */
public class TestEncriptado {
    public static void main(String[] args) throws Exception {
 
        String encriptado = UtilService.Encriptar("Esto es una prueba");
        System.out.println(encriptado);
        String desencriptado = UtilService.Desencriptar(encriptado);
        System.out.println(desencriptado);
 
    }
}
