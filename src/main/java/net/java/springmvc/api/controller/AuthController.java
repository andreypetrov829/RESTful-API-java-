package net.java.springmvc.api.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/auth")
public class AuthController {

	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	public ResponseEntity<String> showSomething() {
		String str = "123";
		return new ResponseEntity<String>(str, HttpStatus.OK) ;
	}
}
