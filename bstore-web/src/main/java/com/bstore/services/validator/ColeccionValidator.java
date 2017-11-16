package com.bstore.services.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bstore.services.persistence.pojo.Coleccion;

@Component
public class ColeccionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Coleccion.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "coleccion.nombre.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreMostrar", "coleccion.nombremostrar.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "portadaUrl", "coleccion.urlportada.novacio");
	}
	
}
