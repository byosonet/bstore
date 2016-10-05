package com.bstore.services.util;

import javax.servlet.http.HttpSession;

public class ValidarSesion {
	public static String FORBIDDEN = "forbidden";
	public static String MSG_FORBIDDEN = "::: -> El tiempo de la sesion ha caducado <-:::";
	
	public static String validarSesionUsuarioActual(HttpSession session) {
		if (session != null && session instanceof HttpSession && session.getAttribute("token") != null) {
			return "::: -> "+session.getAttribute("token").toString()+" <-:::";
		} else {
			return FORBIDDEN;
		}
	}
}
