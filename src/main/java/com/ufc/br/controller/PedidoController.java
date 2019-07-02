package com.ufc.br.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Carrinho;
import com.ufc.br.model.Cliente;
import com.ufc.br.model.Item;
import com.ufc.br.model.Pedido;
import com.ufc.br.service.ClienteService;
import com.ufc.br.service.ItemService;
import com.ufc.br.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoServ;

    @Autowired
    private ItemService itemServ;

    @Autowired
    private ClienteService clienteServ;

    @RequestMapping("/confirmar")
    public ModelAndView salvar(HttpSession session) {

        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
       
        if(carrinho.getItens().size() < 1)
            return new ModelAndView("redirect:/gerente/listar");

        Pedido pedido = new Pedido();

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = user.getUsername();
		System.out.println(email);

        Cliente cliente = clienteServ.buscarPorEmail(email);
        pedido.setCliente(cliente);
        pedido.setTotal( carrinho.calcTotal() );
        
        pedido = pedidoServ.salvar(pedido);
        
        for (Item item : carrinho.getItens()) {
            item.setPedido(pedido);
            itemServ.cadastraItem(item);    
        }
        
        pedido.setItens(carrinho.getItens());
        
        pedidoServ.salvar(pedido);

       // pedido.setPendente(1);

        session.removeAttribute("carrinho");
        ModelAndView mv = new ModelAndView("redirect:/gerente/listar");
        return mv;
    }
//
    @RequestMapping("/listar")
    public ModelAndView listar() {
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  		String email = user.getUsername();

        Cliente cliente = clienteServ.buscarPorEmail(email);

        List<Pedido> listaPedidos = pedidoServ.buscarCliente(cliente);

        ModelAndView mv = new ModelAndView("Pedidos/ListarPedidos");
        mv.addObject("pedidos", listaPedidos);
        return mv;
    }
    
	 
    @RequestMapping("/listaritens/{codigo}")
	    public ModelAndView listarItens(@PathVariable Long codigo) {
	    	Pedido pedido = pedidoServ.buscarPorId(codigo);
	        System.out.println(pedido.getItens()); 
	        ModelAndView mv = new ModelAndView("Pedidos/VerItens");
	        mv.addObject("pedido", pedido);
	        return mv;
	    }



}