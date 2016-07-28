package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Usuario;

public interface UsuarioDao {
	List<Usuario> getUser();
    Usuario validaUsuario(String email, String password);
    void actualizarConexionUsuario(Usuario usuario);
    void agregarUsuario(Usuario usuario);
    Usuario validaEmailSistema(String email);
    Usuario validaLoginSistema(String login);
    void actualizarDatosUsuario(Usuario user);
    void deleteUser(Usuario usuario);
    Usuario byId(int idUser);
    List<Usuario> getListaEmailNotificaciones(String notificar);
}
