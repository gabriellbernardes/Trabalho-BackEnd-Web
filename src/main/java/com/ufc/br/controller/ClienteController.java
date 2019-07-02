package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.service.ClienteService;


@RequestMapping("/cliente")
@Controller
public class ClienteController {
	@Autowired
	private ClienteService clienteServ;
	
	    @RequestMapping(value = "/cadastrar")
	    public ModelAndView cadastrar() {
	        ModelAndView mv = new ModelAndView("Cliente/CadastroCli");
	        mv.addObject("cliente", new Cliente());
	        return mv;
	    }
	 
	    @PostMapping("/salvar")
	    public ModelAndView salvar(@Validated Cliente cliente, BindingResult result) {
	        if (result.hasErrors()) {
	            ModelAndView mv = new ModelAndView("Cliente/CadastroCli");
	            return mv;
	        }
	        clienteServ.save(cliente);
	        ModelAndView mv = new ModelAndView("index");
	        return mv;
	    }

	    @GetMapping("/listar")
	    public ModelAndView listar() {
	        List<Cliente> clientes = clienteServ.listarClientes();
	        ModelAndView mv = new ModelAndView("cliente/ListadeClientes");
	        mv.addObject("listaClientes", clientes);

	        return mv;
	    }

	    @RequestMapping("/excluir/{codigo}")
	    public ModelAndView excluir(@PathVariable Long codigo) {
	        clienteServ.excluirCliente(codigo);;
	        ModelAndView mv = new ModelAndView("redirect:/prato/listar_cliente");
	        return mv;
	    }

	    @RequestMapping("/atualizar")
	    public ModelAndView atualizar() {

	        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        UserDetails user = (UserDetails) auth;

	        Cliente cliente = clienteServ.buscarPorEmail(user.getUsername());

	        ModelAndView mv = new ModelAndView("cliente/cadastrar_cliente");
	        mv.addObject("cliente", cliente);
	        return mv;
	    }
}
