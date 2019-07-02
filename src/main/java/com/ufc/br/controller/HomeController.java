package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.GerenteService;

@Controller
public class HomeController {
	@Autowired
	private GerenteService gerenteServ;
	
	@GetMapping("/")
	public ModelAndView inicio() {		
		ModelAndView mv = new ModelAndView("index");
		List<Prato> pratos = gerenteServ.listarPratos();
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView logar() {		
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

}
