//package com.bstore.services.test;
//
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.bstore.services.persistence.pojo.Perfil;
//import com.bstore.services.persistence.pojo.Usuario;
//import com.bstore.services.service.UsuarioService;
//
//@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
//@RunWith(SpringJUnit4ClassRunner.class)
//public class UsuarioServiceTest {
//	private Logger logger = Logger.getLogger(UsuarioServiceTest.class);
//	
//	@Autowired
//	private UsuarioService usuarioService;
//	
//	@Test
//	public void testGetListaUsuarios(){
//		logger.info("testGetListaUsuarios");
//		List<Usuario> usuarioList = usuarioService.getListaUsuarios();
//		
//		if(usuarioList != null){
//			logger.info("usuarioList.size(): "+usuarioList.size());
//		}
//	}
//	
//	@Test
//	public void testValidaUsuario(){
//		logger.info("testValidaUsuario");
//		Usuario usuarioValido = usuarioService.validaUsuario("dev@bstore", "dev");
//		
//		if(usuarioValido != null){
//			logger.info("El usuario existe en la base de datos");
//		}else{
//			logger.info("El usuario no se encontró");
//		}
//			
//	}
//	
//	@Test
//	public void testActulizarConexionUsuario(){
//		logger.info("testActulizarConexionUsuario");
//		
//		Usuario usuario = new Usuario();
//		usuario.setId(1);
//		
//		usuarioService.actulizarConexionUsuario(usuario);
//	}
//	
//	@Test
//	public void testAgregaUsuarioNuevo(){
//		logger.info("testAgregaUsuarioNuevo");
//		
//		Usuario usuario = new Usuario();
//		usuario.setId(100);
//		usuario.setNombre("Nuevo usuario");
//		usuario.setAPaterno("Apellido Paterno");
//		usuario.setAMaterno("Apellido Materno");
//		usuario.setEmail("mail@gmail.com");
//		usuario.setEstatus(1);
//		usuario.setFechaAlta(new Date());
//		usuario.setFechaNacimiento(new Date());
//		usuario.setLogin("user");
//		usuario.setPassword("password");
//		Perfil perfil = new Perfil();
//		perfil.setId(1);
//		usuario.setPerfil(perfil);
//		
//		int usuarioId = usuarioService.agregaUsuarioNuevo(usuario);
//		if(usuarioId != 0){
//			logger.info("El usuario se agregó correctamente, usuarioId: "+usuarioId);
//		}else{
//			logger.info("Error al agregar al usuario");
//		}
//	}
//	
//	@Test
//	public void testValidaEmailSistema(){
//		logger.info("testValidaEmailSistema");
//		String email = "dev@bstore";
//		Usuario usuario = usuarioService.validaEmailSistema(email);
//		
//		if(usuario != null){
//			logger.info("El usuario con el email: "+email+" es correcto (existe)");
//		}else{
//			logger.info("El usuario con el email: "+email+" es incorrecto (no existe)");
//		}
//	}
//	
//	@Test
//	public void testActualizarDatosUsuario(){
//		logger.info("testActualizarDatosUsuario");
//		
//		Usuario usuario = new Usuario();
//		usuario.setId(1);
//		usuario.setNombre("Nuevo nombre");
//		usuario.setAPaterno("Nuevo apaterno");
//		usuario.setAMaterno("Nuevo amaterno");
//		usuario.setEmail("Nuevo email");
//		
//		usuarioService.actualizarDatosUsuario(usuario);
//	}
//	
//	@Test
//	public void testDeleteUser(){
//		logger.info("testDeleteUser");
//		
//		Usuario usuario = new Usuario();
//		usuario.setId(1);
//		usuario.set
//		
//		usuarioService.deleteUser(usuario);
//	}
//	
//	@Test
//	public void testByIdUser(){
//		logger.info("testByIdUser");
//		
//		int userId = 1;
//		
//		Usuario usuario = usuarioService.byIdUser(userId);
//		
//		if(usuario != null){
//			logger.info("Se encontró el usuario con id: "+userId);
//			logger.info("Nombre: "+usuario.getNombre());
//			logger.info("Apellido Paterno: "+usuario.getAPaterno());
//			logger.info("Apellido Materno: "+usuario.getAMaterno());
//		}else{
//			logger.info("No se halló usuario con id: "+userId);
//		}
//	}
//}
