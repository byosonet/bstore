package com.bstore.services.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bstore.services.persistence.pojo.Editorial;

@Component
public class EditorialValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Editorial.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "editorial.nombre.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rfc", "editorial.rfc.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "editorial.email.novacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "editorial.telefono.novacio");
	}
	
}
