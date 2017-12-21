package com.bioskopi.isa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestCtrl {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	ResponseEntity<String> home() {
		return new ResponseEntity<>("a",HttpStatus.OK);
	}
}
