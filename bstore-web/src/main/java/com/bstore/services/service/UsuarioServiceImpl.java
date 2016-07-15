package com.bstore.services.service;

import com.bstore.services.persistence.dao.UsuarioDao;
import com.bstore.services.persistence.pojo.Usuario;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Component("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
private final Logger log = Logger.getLogger(UsuarioServiceImpl.class);

    @Transactional
    public List<Usuario> getListaUsuarios() {
        this.log.info(" -- Obteniendo lista de usuarios::::");
        List<Usuario> lista = this.usuarioDao.getUser();
        if(!lista.isEmpty()){
            for(Usuario u: lista){
                 this.log.info(" idUsuario: "+u.getId()+" Nombre: "+u.getNombre());
            } 
        }
        return lista;
    }
    
    
@Autowired
private UsuarioDao usuarioDao;

    @Transactional
    public Usuario validaUsuario(String email, String password) {
        this.log.info(" -- Validando Usuario by: "+email);
        Usuario usuario = this.usuarioDao.validaUsuario(email, password);
        if(usuario!=null){
            this.log.info(" -- Usuario encontrado llamado: "+usuario.getNombre());
        }else{
            this.log.info(" -- Usuario no fue localizado by: "+email);
        }
        return usuario;
    }

    @Transactional
    public void actulizarConexionUsuario(Usuario usuario) {
        this.log.info(" -- Actulizando la ult conexion del usuario: "+usuario.getNombre());
        this.usuarioDao.actualizarConexionUsuario(usuario);
        this.log.info(" -- Actualizacion correcta");
    }

    @Transactional
    public int agregaUsuarioNuevo(Usuario usuario) {
        this.log.info(" -- Agregar usuario a la BD");
        int id = this.usuarioDao.agregarUsuario(usuario);
        return id;
    }

    @Transactional
    public Usuario validaEmailSistema(String email) {
        this.log.info(" -- Validando email en sistema: "+email);
        Usuario user = this.usuarioDao.validaEmailSistema(email);
        return user;
    }

    @Transactional
    public void actualizarDatosUsuario(Usuario usuario) {
        this.log.info(" -- Actualizando Datos del usuario en sistema: "+usuario.toString());
        this.usuarioDao.actualizarDatosUsuario(usuario);
        this.log.info(" -- Datos Actualizados");
    }

    @Transactional
    public void deleteUser(Usuario user) {
        this.usuarioDao.deleteUser(user);
        this.log.info(" -- Usuario elimnado de la BD: "+user.toString());
    }

    @Transactional
    public Usuario byIdUser(int idUser) {
        this.log.info(" -- Buscando por id de usuario: "+idUser);
        return this.usuarioDao.byId(idUser);
    }

	@Transactional
	public Usuario validaLoginSistema(String login) {
		this.log.info(" -- Validando login en sistema: "+login);
        Usuario user = this.usuarioDao.validaLoginSistema(login);
        return user;
	}
    
}
