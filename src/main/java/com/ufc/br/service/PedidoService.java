package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Pedido;
import com.ufc.br.repository.PedidoRepository;


@Service
public class PedidoService {
	

    @Autowired
    private PedidoRepository pedidoRepo;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    public void deletar(Long id) {
        pedidoRepo.deleteById(id);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepo.getOne(id);
    }

    public List<Pedido> buscarCliente(Cliente cliente) {
        return pedidoRepo.findByCliente(cliente);
    }

    public List<Pedido> buscartodos() {
        return pedidoRepo.findAll();
    }

}
