package com.bstore.services.conekta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bstore.services.conekta.service.RequestCharges;
import com.bstore.services.conekta.service.RequestChargesMSI;
import com.bstore.services.conekta.service.RequestPaymentCard;
import com.bstore.services.conekta.service.ResponseCharges;
import com.bstore.services.conekta.service.ResponseChargesMSI;
import com.bstore.services.conekta.service.ResponsePaymentCard;

public class ConektaAdapterImpl extends ConektaAdapterAbstract implements ConektaAdapter{
	
    public ResponseCharges createChargeOxxo(RequestCharges requestCharges) throws Exception {
        ResponseCharges response = new ResponseCharges();
        ResponseCharges.Errors errors = new ResponseCharges.Errors();
        JSONObject res = null;
        
        try {
            logger.info("Request charges: " + requestCharges);
            JSONObject dataObject = new JSONObject(requestCharges);          
            res = execute("POST", dataObject);
            logger.info("Response Server Conekta: " + res);
            
            if(!res.isNull("isError")) throw new Exception();  
            
            /*Preparando response del servicio*/
            response.setId(res.getString("id"));
            response.setLivemode(res.getString("livemode"));
            response.setCreated_at(res.getString("created_at"));
            response.setStatus(res.getString("status"));
            response.setCurrency(res.getString("currency"));
            response.setDescription(res.getString("description"));
            response.setReference_id(res.getString("reference_id"));
            response.setAmount(res.getLong("amount"));
            response.setFailure_code(res.getString("failure_code"));
            response.setFailure_message(res.getString("failure_message"));
            response.setObject(res.getString("object"));
            
            ResponseCharges.Payment_method payment_method 
                    = new ResponseCharges.Payment_method();
            
            JSONObject pay = new JSONObject(res.get("payment_method").toString());
            
            logger.info("Pay method POST: " + pay);
            payment_method.setExpiry_date(pay.getString("expiry_date"));
            payment_method.setBarcode(pay.getString("barcode"));
            payment_method.setBarcode_url(pay.getString("barcode_url"));
            payment_method.setObject(pay.getString("object"));
            payment_method.setType(pay.getString("type"));
            
            response.setPayment_method(payment_method);
            
            ResponseCharges.Details details 
                    = new ResponseCharges.Details();
            
            JSONObject det = new JSONObject(res.get("details").toString());
            logger.info("Details method POST: " + det);
            details.setName(det.getString("name"));
            details.setPhone(det.getString("phone"));
            details.setEmail(det.getString("email"));
            
            response.setDetails(details);
            errors.setIfError(false);
            response.setErrors(errors);
            
        } catch (Exception e) {
            try {
                errors.setStatus(Integer.toString(res.isNull("status")?(int)0:res.getInt("status")));
                errors.setMessage(res.isNull("message")?"":res.getString("message"));
                errors.setError1(res.getString("error1"));
                errors.setError2(res.getString("error2"));
                errors.setIfError(true);
                response.setErrors(errors);
            } catch (Exception ex) {
                logger.info("No hay conectividad con el servicio de Conekta, favor de intentar más tarde.");
                 ex.printStackTrace();
            }
        }
        
        return response;
    }

    public ResponseCharges getInfoChargeOxxo(String idOperation) throws Exception {
       ResponseCharges response = new ResponseCharges();
       ResponseCharges.Errors errors = new ResponseCharges.Errors();
       
        HttpURLConnection conn = null;

        try {
            logger.info("Url apiConekta: " + getUrl()+"?id="+idOperation);
            logger.info("apikey Conekta: " + getApiKey());
            String urlconekta = getUrl()+"?id="+idOperation;
            URL obj = new URL(urlconekta);
            conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestProperty("Accept", "application/vnd.conekta-v0.2.0+json");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestMethod("GET");
            conn.setDoOutput(false);

            String authString = getApiKey() + ":" + "";
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String text = reader.readLine();

            if (conn.getResponseCode() == 200) {
                logger.info("Status de la peticion conekta getInfoChargeOxxo: " + conn.getResponseCode());
                logger.info("Method de la peticion conekta getInfoChargeOxxo: " + conn.getRequestMethod());
                logger.info("Mesnaje del Server de la peticion conekta getInfoChargeOxxo: " + conn.getResponseMessage());

                JSONArray res = new JSONArray(text.toString());
                logger.info("Response Server Conekta: " + res);
                
                /*Preparando response del servicio*/
                
                response.setId(res.getJSONObject(0).getString("id"));
                response.setLivemode(res.getJSONObject(0).getString("livemode"));
                logger.info("LiveMode: "+res.getJSONObject(0).getString("livemode"));
                response.setCreated_at(res.getJSONObject(0).getString("created_at"));
                response.setStatus(res.getJSONObject(0).getString("status"));
                response.setCurrency(res.getJSONObject(0).getString("currency"));
                response.setDescription(res.getJSONObject(0).getString("description"));
                response.setReference_id(res.getJSONObject(0).getString("reference_id"));
                response.setAmount(res.getJSONObject(0).getLong("amount"));
                response.setFailure_code(res.getJSONObject(0).getString("failure_code"));
                response.setFailure_message(res.getJSONObject(0).getString("failure_message"));
                response.setObject(res.getJSONObject(0).getString("object"));
                
                ResponseCharges.Payment_method payment_method 
                        = new ResponseCharges.Payment_method();
                
                JSONObject pay = new JSONObject(res.getJSONObject(0).get("payment_method").toString());
                logger.info("Pay method GET: " + pay);
                payment_method.setExpiry_date(pay.getString("expiry_date"));
                payment_method.setBarcode(pay.getString("barcode"));
                payment_method.setBarcode_url(pay.getString("barcode_url"));
                payment_method.setObject(pay.getString("object"));
                payment_method.setType(pay.getString("type"));
                
                response.setPayment_method(payment_method);
                
                ResponseCharges.Details details 
                        = new ResponseCharges.Details();
                
                JSONObject det = new JSONObject(res.getJSONObject(0).get("details").toString());
                logger.info("Details method GET: " + pay);
                details.setName(det.getString("name"));
                details.setPhone(det.getString("phone"));
                details.setEmail(det.getString("email"));
                
                response.setDetails(details);
                errors.setIfError(false);
                response.setErrors(errors);

            }
            
        } catch (Exception e) {
            try {
                logger.info("Status: " + conn.getResponseCode());
                logger.info("message: " + conn.getResponseMessage());
                logger.info("Error: " + e.getMessage());
                logger.info("Error: " + e.toString());
                
                errors.setStatus(Integer.toString(conn.getResponseCode()));
                errors.setMessage(conn.getResponseMessage());
                errors.setError1(e.getMessage());
                errors.setError2(e.toString());
                errors.setIfError(true);
                response.setErrors(errors);
            } catch (java.net.UnknownHostException ex) {
                logger.info("No hay conectividad con el servicio de Conekta, favor de intentar m��s tarde.");
                ex.printStackTrace();
            }
        }

        return response;
    }

    public List<ResponseCharges> getAllChargesOxxo(String status) throws Exception {

        List<ResponseCharges> list = new ArrayList<ResponseCharges>();
        //HttpURLConnection conn = null;
        ResponseCharges responseService = new ResponseCharges();
        ResponseCharges.Errors errors = new ResponseCharges.Errors();
        JSONObject response = null;
        
        try {
            /*logger.info("Url apiConekta: " + url);
            logger.info("apikey Conekta: " + apiKey);
            String urlconekta = url;
            URL obj = new URL(urlconekta);
            conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestProperty("Accept", "application/vnd.conekta-v0.2.0+json");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestMethod("GET");
            conn.setDoOutput(false);

            String authString = apiKey + ":" + "";
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            conn.setRequestProperty("Authorization", "Basic " + authStringEnc);*/
            
            response = execute("GET", null);
            logger.info("Respuesta server conekta : "+ response.toString());
            
            /*BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String text = reader.readLine();*/

            /*if (conn.getResponseCode() == 200) {
                logger.info("Status de la peticion conekta getAllChargesOxxo: " + conn.getResponseCode());
                logger.info("Method de la peticion conekta getAllChargesOxxo: " + conn.getRequestMethod());
                logger.info("Mesnaje del Server de la peticion conekta getAllChargesOxxo: " + conn.getResponseMessage());*/
                if(!response.isNull("isError")) throw new Exception();  
            
                JSONArray res = new JSONArray(response.toString());
                logger.info("Response Server Conekta: " + res);

                for (int i = 0; i < res.length(); i++) {
                    /*Preparando response del servicio*/
                    if (res.getJSONObject(i).getString("status").equals(status) || status.equals("all")) {
                        responseService.setId(res.getJSONObject(i).getString("id"));
                        responseService.setLivemode(res.getJSONObject(i).getString("livemode"));
                        responseService.setCreated_at(res.getJSONObject(i).getString("created_at"));
                        responseService.setStatus(res.getJSONObject(i).getString("status"));
                        responseService.setCurrency(res.getJSONObject(i).getString("currency"));
                        responseService.setDescription(res.getJSONObject(i).getString("description"));
                        responseService.setReference_id(res.getJSONObject(i).getString("reference_id"));
                        responseService.setAmount(res.getJSONObject(i).getLong("amount"));
                        responseService.setFailure_code(res.getJSONObject(i).getString("failure_code"));
                        responseService.setFailure_message(res.getJSONObject(i).getString("failure_message"));
                        responseService.setObject(res.getJSONObject(i).getString("object"));

                        ResponseCharges.Payment_method payment_method = new ResponseCharges.Payment_method();

                        JSONObject pay = new JSONObject(res.getJSONObject(i).get("payment_method").toString());
                        payment_method.setExpiry_date(pay.getString("expiry_date"));
                        payment_method.setBarcode(pay.getString("barcode"));
                        payment_method.setBarcode_url(pay.getString("barcode_url"));
                        payment_method.setObject(pay.getString("object"));
                        payment_method.setType(pay.getString("type"));

                        responseService.setPayment_method(payment_method);

                        ResponseCharges.Details details = new ResponseCharges.Details();

                        JSONObject det = new JSONObject(res.getJSONObject(i).get("details").toString());
                        details.setName(det.getString("name"));
                        details.setPhone(det.getString("phone"));
                        details.setEmail(det.getString("email"));

                        responseService.setDetails(details);
                        errors.setIfError(false);
                        responseService.setErrors(errors);
                        list.add(responseService);
                    }
                }
           
        } catch (Exception e) {
            try {
                logger.info("Error: " + e.getMessage());
                logger.info("Error: " + e.toString());
                
                errors.setStatus(Integer.toString(response.isNull("status")?(int)0:response.getInt("status")));
                errors.setMessage(response.isNull("message")?"":response.getString("message"));
                errors.setError1(response.getString("error1"));
                errors.setError2(response.getString("error2"));
                errors.setIfError(true);
                responseService.setErrors(errors);
            } catch (Exception ex) {
                logger.info("No hay conectividad con el servicio de Conekta, favor de intentar m��s tarde.");
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    public ResponsePaymentCard createChargeCard(RequestPaymentCard request) throws Exception {
        ResponsePaymentCard responseService = new ResponsePaymentCard();
        ResponsePaymentCard.Errors errors = new ResponsePaymentCard.Errors();
        JSONObject response = null;
        try {
            logger.info("Request: " + request.toString());
            JSONObject dataObject = new JSONObject(request);
            response = execute("POST", dataObject);
            logger.info("Response Server Conekta desde adapterImpl: " + response);
            System.out.println("response " + response);
            
            if(response.getString("object") != null 
            		&& response.getString("object").equals("error")) 
            	throw new Exception();
            
            logger.info("comenzando el llenado de la respuesta ..... ");
            responseService.setId(response.getString("id"));
            responseService.setAmount(new Long(response.getString("amount")));
            responseService.setCreatedAt(new Long(response.getString("created_at")));
            responseService.setCurrency(response.getString("currency"));
            responseService.setDescription(response.getString("description"));
            responseService.setFailureCode(response.getString("failure_code"));
            responseService.setFailureMessage(response.getString("failure_message"));
            responseService.setLivemode(new Boolean(response.getString("livemode")));
            responseService.setObject(response.getString("object"));
            responseService.setReferenceId(response.getString("reference_id"));
            responseService.setStatus(response.getString("status"));

            JSONObject pay = new JSONObject(response.getString("payment_method"));
            ResponsePaymentCard.Payment payment = new ResponsePaymentCard.Payment();
            payment.setAuthCode(pay.getString("auth_code"));
            payment.setExpMonth(pay.getString("exp_month"));
            payment.setExpYear(pay.getString("exp_year"));
            payment.setLast4(pay.getString("last4"));
            payment.setName(pay.getString("name"));
            payment.setBrand(pay.getString("brand"));
            payment.setObject(pay.getString("object"));

            responseService.setPaymentMethod(payment);
        } catch (Exception e) {
            if(response != null){
                errors.setError1(response.getString("message_to_purchaser"));
                errors.setError2(response.getString("message"));
                errors.setIfError(true);
                errors.setMessage(e.getMessage());
                errors.setStatus(response.getString("object"));
                responseService.setError(errors);
            }
        }
        return responseService;
    }
     public ResponseChargesMSI createChargeCardMSI(RequestChargesMSI request) throws Exception {
         ResponseChargesMSI responseService = new ResponseChargesMSI();
         ResponseChargesMSI.Errors errors = new ResponseChargesMSI.Errors();
         JSONObject response = null;
         
         try {
             logger.debug("Request: "+request.toString());
             JSONObject dataObject = new JSONObject(request);
             response = execute("POST", dataObject);
             logger.info("Response Server Conekta desde adapterImpl: " + response);
             System.out.println("response " + response);
             
             if(response.getString("object").toString().equalsIgnoreCase("error")) throw new Exception();
             
             logger.info("comenzando el llenado de la respuesta ..... ");
             responseService.setId(response.get("id").toString());
             responseService.setLivemode(response.get("livemode").toString().equals("true") ? true : false);
             responseService.setCreatedAt(Long.parseLong(response.get("created_at").toString()));
             responseService.setStatus(response.getString("status"));
             responseService.setCurrency(response.get("currency").toString());
             responseService.setDescription(response.get("description").toString());
             responseService.setReferenceId(response.get("reference_id").toString());
             responseService.setFailureCode(response.get("failure_code").toString());
             responseService.setFailureMessage(response.get("failure_message").toString());
             responseService.setMonthlyInstallments(Long.parseLong(response.get("monthly_installments").toString()));
             responseService.setObject(response.get("object").toString());
             responseService.setAmount(Long.parseLong(response.get("amount").toString()));
             responseService.setPaidAt(Long.parseLong(response.get("paid_at").toString()));
             if(response.get("fee").toString() != null)
                responseService.setFee(Long.parseLong(response.get("fee").toString()));
             else 
                 responseService.setFee(null);
             responseService.setCustomerId(response.get("customer_id").toString());
             
             
             JSONObject paymentMethod = new JSONObject(response.getString("payment_method")); 
             
             ResponseChargesMSI.PaymentMethod paymentM = new ResponseChargesMSI.PaymentMethod();
             paymentM.setName(paymentMethod.getString("name"));
             paymentM.setExpMonth(paymentMethod.getString("exp_month"));
             paymentM.setExpYear(paymentMethod.getString("exp_year"));
             paymentM.setAuthCode(paymentMethod.getString("auth_code"));
             paymentM.setObject(paymentMethod.getString("object"));
             paymentM.setLast4(paymentMethod.getString("last4"));
             paymentM.setBrand(paymentMethod.getString("brand"));         
             responseService.setPaymentMethod(paymentM);
             
             
             JSONObject details = new JSONObject(response.getString("details"));
             ResponseChargesMSI.Detail detail = new ResponseChargesMSI.Detail();
             detail.setName(details.getString("name"));
             detail.setPhone(details.getString("phone"));
             detail.setEmail(details.getString("email"));
             responseService.setDetail(detail);
             
         } catch (Exception e) {
             if(response != null){
                 System.out.println("ResponseError: "+response.toString());
                errors.setCode(response.getString("code"));
                errors.setMessage(response.getString("message"));
                errors.setParam(response.getString("param"));
                errors.setMessageToPurchaser("message_to_purchaser");
                errors.setObject(response.getString("object"));
                errors.setType(response.getString("type"));
                errors.setIfError(true);
                responseService.setErrors(errors);
            }
         }
         return responseService;
    }
    
     public ResponseChargesMSI domiCreateChargeCardMSI(RequestChargesMSI request){

         ResponseChargesMSI responseService = new ResponseChargesMSI();
         ResponseChargesMSI.PaymentMethod paymentMethod = new ResponseChargesMSI.PaymentMethod();
         
         responseService.setId("51e5ebfeaef878f39e0000d0");
         responseService.setAmount(Long.parseLong(request.getAmount().toString()));
         responseService.setCurrency(request.getCurrency());
         responseService.setDescription(request.getDescription());
         responseService.setCreatedAt(Long.parseLong("1374005408"));
         responseService.setLivemode(true);
         responseService.setMonthlyInstallments(Long.parseLong(request.getMonthly_installments()));
         
         paymentMethod.setBrand("VISA|MC");
         paymentMethod.setExpMonth(request.getCard().getExp_month());
         paymentMethod.setExpYear(request.getCard().getExp_year());
         paymentMethod.setLast4(request.getCard().getNumber()
                 .substring(request.getCard().getNumber().length()-4, request.getCard().getNumber().length()));
         paymentMethod.setObject("card_payment");
         responseService.setPaymentMethod(paymentMethod);
         return responseService;
     }

    private static final Logger logger=Logger.getLogger(ConektaAdapterImpl.class);
}
