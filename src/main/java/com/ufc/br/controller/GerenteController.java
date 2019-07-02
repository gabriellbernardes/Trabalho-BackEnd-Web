package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ufc.br.model.Prato;
import com.ufc.br.service.GerenteService;

@Controller
@RequestMapping("/gerente")
public class GerenteController {
	
	@Autowired
	private GerenteService gerenteServ;
	
	@RequestMapping("/adicionar")
	public ModelAndView adicionaPrato(Prato prato) {
		ModelAndView mv = new ModelAndView("Gerente/PaginaGerente");
		mv.addObject("prato", prato);
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Validated Prato prato, BindingResult result, @RequestParam(value ="imagem") MultipartFile imagem) {
		//cadastrar no bd
		ModelAndView mv = new ModelAndView("redirect:/gerente/listar");	
		
		if(result.hasErrors()) {
			return adicionaPrato(prato);
		}	
		
		gerenteServ.cadastraPrato(prato, imagem);
		mv.addObject("mensagem", "Prato cadastrado com sucesso!");

		return mv;
	}
	

	@GetMapping("/listar")
	public ModelAndView listarPratos() {
		List<Prato> pratos = gerenteServ.listarPratos();
		ModelAndView mv = new ModelAndView("Gerente/ListagemPratos");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo){
		gerenteServ.excluirPrato(codigo);
		ModelAndView mv = new ModelAndView("redirect:/gerente/listar");
		return mv;
	}
	
	@RequestMapping("/atualizar/{codigo}")
	public ModelAndView atualizar (@PathVariable Long codigo) {
		Prato prato = gerenteServ.buscarPorId(codigo);
		ModelAndView mv = new ModelAndView("Gerente/PaginaGerente");
		mv.addObject("prato", prato);
		return mv;
	}
	
	

}
