package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Item;
import com.ufc.br.model.Pedido;
import com.ufc.br.repository.ItemRepository;


@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;
	
	public void cadastraItem(Item item){
		itemRepo.save(item);	
	}
		
	public List<Item> listarItens() {
		
		return itemRepo.findAll();
		}

	public Item buscarPorId(Long codigo) {
			return itemRepo.getOne(codigo);
			
		}
	
	public void salvarTudo(ArrayList<Item> itens) {
        itemRepo.saveAll(itens);
    }


    public void excluir(Long id) {
        itemRepo.deleteById(id);
    }

  

    public List<Item> buscarItem(Pedido pedido) {
        return itemRepo.findByPedido(pedido);
    }


}
