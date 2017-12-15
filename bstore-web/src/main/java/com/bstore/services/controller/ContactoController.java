package com.bstore.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bstore.services.service.EnviarEmailService;

@Controller
public class ContactoController {
	private final Logger LOGGER = Logger.getLogger(ContactoController.class);
	
	@Autowired
	EnviarEmailService enviarEmailService;
}
