package com.bstore.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.service.CompraService;
import com.bstore.services.service.EditorialService;

/**
 * 
 * @author hustler
 *
 */
@Controller
public class EditorialController {

	private Logger logger = Logger.getLogger(EditorialController.class);

	@Autowired
	private EditorialService editorialService;

	@RequestMapping(value="/editorial/getAll",method = RequestMethod.GET)
	public String getAll(Model model, HttpServletRequest request){
		logger.info("editorialController.getAll()");

		List<Editorial> editorialList = editorialService.getAll();
		if(editorialList != null){
			model.addAttribute("editoriales",editorialList);
		}
		return "editoriales";
	}

	@RequestMapping(value="/editorial/add",method = RequestMethod.GET)
	public String editorialAdd(Model model, HttpServletRequest request){
		logger.info("editorialController.editorialAdd()");
		
		return "editorialAdd";
	}
}
