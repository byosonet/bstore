/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstore.services.service;

import com.bstore.services.model.UserSession;
import com.bstore.services.persistence.pojo.Usuario;
import java.util.List;

/**
 *
 * @author gtrejo
 */
public interface UsuarioService {
    List<Usuario> getListaUsuarios();
    Usuario validaUsuario(String email, String password);
    UserSession validaUsuarioModel(String email, String password);
    void actulizarConexionUsuario(Usuario usuario);
    void agregaUsuarioNuevo(Usuario usuario);
    Usuario validaEmailSistema(String email);
    Usuario validaLoginSistema(String login);
    void actualizarDatosUsuario(Usuario usuario);
    void deleteUser(Usuario user);
    Usuario byIdUser(int idUser);
}
