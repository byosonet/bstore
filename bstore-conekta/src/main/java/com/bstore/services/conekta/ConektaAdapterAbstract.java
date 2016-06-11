package com.bstore.services.conekta;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONObject;


public abstract class ConektaAdapterAbstract {

protected JSONObject execute(String method, JSONObject request) throws Exception {
        logger.info("CONEKTA:API:URL: " + url);
        logger.info("CONEKTA:API:KEY: " + apiKey);

        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        JSONObject response = null;
        StringBuilder sb = null;
        
        try {
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            
            connection.setRequestProperty("Accept", "application/vnd.conekta-v0.3.0+json"); 
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Conekta-Client-User-Agent", "{\"agent\":\"Conekta JavascriptBindings/0.3.0\"}");
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            
            String authString = apiKey + ":" + "";
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
            
            if(request != null){
                String data = request.toString();
                logger.info("Request: " + data);
                
                out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(data);
                out.flush();
                out.close();
            }
            
            logger.info("Status de la peticion Conekta: "
                        + connection.getResponseCode());
            
            if (connection.getResponseCode() == 200)
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            else
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8")); 
            
            String text = "";
                sb = new StringBuilder();
                while((text = reader.readLine()) != null)
                    sb.append(text);

            logger.info("Method de la peticion Conekta: "
                    + connection.getRequestMethod());
            logger.info("Mesanje del Server de la peticion Conekta: "
                    + connection.getResponseMessage());
            response = new JSONObject(sb.toString());
            
        } catch (Exception e) {
            logger.info("Error en servicio conekta ... ");
            if(response == null ) {
                response = new JSONObject(sb.toString());
                response.append("isError", true);
            }
            //e.printStackTrace();
        }
        
        return response;
    }
	
    private String apiKey;
    private String url;
    
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    private static final Logger logger = Logger.getLogger(ConektaAdapterAbstract.class);
}
