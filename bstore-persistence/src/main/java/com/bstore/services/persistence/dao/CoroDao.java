package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.hbm.Coro;
import java.util.List;

/**
 *
 * @author User
 */
public interface CoroDao {
    List<Coro> obtenerListaCoroActualizada();
    List<Coro> obtenerListacCompletaCoro();
    Coro obtenerCoro(String idCoro);
    int agregarCoro(Coro coro);
    boolean validarNumeroCoro(String numCoro);
    boolean validarNombreCoro(String nombre);
    
    List<Coro> getListaCorosPendientes();
    
    Coro getCoroById(int idCoro);
    void deleteCoro(Coro coro);
    void updateCoro(Coro coro);
    
    Coro getByNumCoro(String numCoro);
}
