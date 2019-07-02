package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<Item> itens;
	private double valorTotal;
	
	public Carrinho() {
		itens = new ArrayList<Item>();
		valorTotal = 0;
	}
	
	public void adicionarPrato( Prato prato ) {
		
		for( Item i : itens ) {
			
			if( i.getPrato().getCodigo().equals(prato.getCodigo()) ) {
				
				i.setQuantidade( i.getQuantidade() + 1 );
				return;
			}
			
		}
		Item novo_item = new Item();
		novo_item.setPrato(prato);
		novo_item.setQuantidade(1);	
		itens.add(novo_item);
		
	}
	
	public void removerPrato( Prato prato ) {
		
       for( Item i : itens ) {
			
			if( i.getPrato().getCodigo().equals(prato.getCodigo()) ) {
				
				itens.remove(i);
				return;
			}
			
		}
		
	}
	
	public void diminuirQuant( Prato prato ) {
		
	       for( Item i : itens ) {
				
				if( i.getPrato().getCodigo().equals(prato.getCodigo())) {
					
					i.setQuantidade( i.getQuantidade() - 1 );
					if( i.getQuantidade() <= 0 ) {
						removerPrato(prato);
					}
					return;
				}
				
			}
			
	}
	
	public void aumentarQuant( Prato prato ) {
		
	       for( Item i : itens ) {
				
				if( i.getPrato().getCodigo().equals(prato.getCodigo()) ) {
					
					i.setQuantidade( i.getQuantidade() + 1 );
					return;
				}
				
			}
			
	}
	
	public double calcTotal() {
		double valorTotal= 0;
		for(Item i : itens) {
			valorTotal+= i.getPrato().getPreco().doubleValue() *i.getQuantidade();
		}
		return this.valorTotal = valorTotal;
		
	}
	
	
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
