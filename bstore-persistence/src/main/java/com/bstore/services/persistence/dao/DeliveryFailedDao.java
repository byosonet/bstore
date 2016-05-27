package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.hbm.DeliveryFailed;
import java.util.List;

/**
 *
 * @author User
 */
public interface DeliveryFailedDao {
    void guardarDeliveryFailed(DeliveryFailed deliveryFailed);
    List<DeliveryFailed> getListMailFailed();
    void deleteDeliveryFailed(DeliveryFailed df);
    DeliveryFailed getById(int idDelivery);
}
