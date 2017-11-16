package com.bstore.services.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author gtrejo
 */
public class UtilService {
	
	public static void main (String args[]){
		String mail = "prueba@gmail.com";
		String token = Encriptar(mail);
		System.out.println("Encriptar mail: "+mail);
		System.out.println("Encriptado: "+token);
		try{
		System.out.println("Desencriptado:  "+Desencriptar(token));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
    public static String Encriptar(String password){
        String secretKey = "soloadioslagloria";
        String base64EncryptedString = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64Bytes = Base64.encodeBase64(base64Bytes);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return base64EncryptedString.replace("/", "_");
    }
    
    public static String Desencriptar(String password) throws Exception {
        password = password.replace("_", "/");
        String secretKey = "soloadioslagloria";
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(password.getBytes("utf-8"));
            message = Base64.decodeBase64(message);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return base64EncryptedString;
}
    public static int calcularEdad(String fecha) {
        String datetext = fecha;
        try {
            Calendar birth = new GregorianCalendar();
            Calendar today = new GregorianCalendar();
            int age = 0;
            int factor = 0;
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(datetext);
            Date currentDate = new Date();
            birth.setTime(birthDate);
            today.setTime(currentDate);
            if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
                if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
                    if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) {
                        factor = -1;
                    }
                } else {
                    factor = -1;
                }
            }
            age = (today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)) + factor;
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("No se puede calcular la edad");
            return -1;
        }
    }
    
    public static Timestamp getFechaTimeStamp(){
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        return stamp;
    }
    
    public static String generateTokenMD5() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("No se puede calcular HASH MD5:" + e.getMessage());
            throw new RuntimeException(e);
        }

        StringBuilder hexString = new StringBuilder();
        byte[] data = md.digest(RandomStringUtils.randomAlphabetic(10).getBytes());
        for (int i = 0; i < data.length; i++) {
            hexString.append(Integer.toHexString((data[i] >> 4) & 0x0F));
            hexString.append(Integer.toHexString(data[i] & 0x0F));
        }
        System.out.println("HASH MD5 Calculado === " + hexString.toString());
        return hexString.toString();
    }
}
