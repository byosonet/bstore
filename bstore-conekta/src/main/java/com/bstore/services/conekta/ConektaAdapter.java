
package com.bstore.services.conekta;

import java.util.List;

import com.bstore.services.conekta.service.RequestCharges;
import com.bstore.services.conekta.service.RequestChargesMSI;
import com.bstore.services.conekta.service.RequestPaymentCard;
import com.bstore.services.conekta.service.ResponseCharges;
import com.bstore.services.conekta.service.ResponseChargesMSI;
import com.bstore.services.conekta.service.ResponsePaymentCard;

/**
 *
 * @author gtrejo
 */
public interface ConektaAdapter {
    public ResponseCharges createChargeOxxo(RequestCharges requestCharges) throws Exception;
    public ResponseCharges getInfoChargeOxxo(String idOperation) throws Exception;
    public List<ResponseCharges> getAllChargesOxxo(String status) throws Exception;
    ResponsePaymentCard createChargeCard(RequestPaymentCard requestPaymentCard) throws Exception;
    ResponseChargesMSI createChargeCardMSI(RequestChargesMSI request) throws Exception;
    ResponseChargesMSI domiCreateChargeCardMSI(RequestChargesMSI request);
}
