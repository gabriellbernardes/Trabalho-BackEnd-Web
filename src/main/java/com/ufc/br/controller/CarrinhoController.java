package com.ufc.br.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Carrinho;
import com.ufc.br.repository.PratoRepository;


@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	@Autowired
	PratoRepository pratoRepo;

	@RequestMapping("/adicionar/{codigo}")
	public ModelAndView cadastrar(@PathVariable Long codigo, HttpSession session) {
		Carrinho carrinho;
			if(session.getAttribute("carrinho") == null) {
				carrinho = new Carrinho();
			}else {
				carrinho = (Carrinho)session.getAttribute("carrinho");
				}
			carrinho.adicionarPrato(pratoRepo.getOne(codigo));
			session.setAttribute("carrinho", carrinho);
			System.out.println(carrinho.getItens().get(0).getQuantidade());
		ModelAndView mv = new ModelAndView("PaginaCarrinho");
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("PaginaCarrinho");
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo, HttpSession session) {
		Carrinho carrinho;
		if(session.getAttribute("carrinho") == null) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
	
		}
		
		carrinho.removerPrato(pratoRepo.getOne(codigo));
		session.setAttribute("carrinho", carrinho);
		ModelAndView mv = new ModelAndView("PaginaCarrinho");
		return mv;
	}
	
	@RequestMapping("/aumentarqtd/{codigo}")
	public ModelAndView aumentar(@PathVariable Long codigo, HttpSession session) {
		Carrinho carrinho;
		if(session.getAttribute("carrinho") == null) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
	
		}
		
		carrinho.aumentarQuant(pratoRepo.getOne(codigo));
		session.setAttribute("carrinho", carrinho);
		ModelAndView mv = new ModelAndView("PaginaCarrinho");
		return mv;
	}
	
	@RequestMapping("/diminuirqtd/{codigo}")
	public ModelAndView diminuir(@PathVariable Long codigo, HttpSession session) {
		Carrinho carrinho;
		if(session.getAttribute("carrinho") == null) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
	
		}
		
		carrinho.diminuirQuant(pratoRepo.getOne(codigo));
		session.setAttribute("carrinho", carrinho);
		ModelAndView mv = new ModelAndView("PaginaCarrinho");
		return mv;
	}
	
	
	
	
			
}
