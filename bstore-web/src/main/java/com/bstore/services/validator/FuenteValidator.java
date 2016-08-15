package com.bstore.services.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bstore.services.persistence.pojo.Fuente;

@Component
public class FuenteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Fuente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreBiblioteca", "fuente.nombrebiblioteca.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paginaWeb", "fuente.paginaweb.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "fuente.email.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "fuente.telefono.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rfc", "fuente.rfc.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "autor", "fuente.autor.novacio");
	}
	
}
