package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.dto.ConsultaCoro;
import com.bstore.services.persistence.hbm.Changeset;
import com.bstore.services.persistence.hbm.TipoMovimientoEnum;
import java.util.List;

/**
 *
 * @author User
 */
public interface ChangesetDao {
    void guardarChangeset(Changeset changeset);
    List<Changeset> getChangesetUser(String idUser);
    int totalMovemente(TipoMovimientoEnum movement, String idUser);
    List<ConsultaCoro> getConsultaCoro(int days);
}