package com.bstore.services.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bstore.services.persistence.pojo.Publicacion;

@Component
public class PublicacionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Publicacion.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "publicacion.nombre.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroPaginas", "publicacion.numeroPaginas.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "portada", "publicacion.portada.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "publicacion.isbn.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "publicacion.precio.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "urlArchivo", "publicacion.urlArchivo.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "portadaUrl", "publicacion.portadaUrl.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resumen", "publicacion.resumen.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descuento", "publicacion.descuento.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estatus", "publicacion.estatus.novacio");
		
	}
	
}
