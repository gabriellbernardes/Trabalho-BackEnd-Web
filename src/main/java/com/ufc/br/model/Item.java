package com.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idItem;

	    @ManyToOne
	    private Pedido pedido;

	    @ManyToOne
	    private Prato prato;

	    private int quantidade;
	    private Double preco;    
	    
	    public Long getIdItem() {
			return idItem;
		}
		public void setIdItem(Long idItem) {
			this.idItem = idItem;
		}
		public Pedido getPedido() {
			return pedido;
		}
		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}
		public Prato getPrato() {
			return prato;
		}
		public void setPrato(Prato prato) {
			this.prato = prato;
		}
		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
		public Double getPreco() {
			return preco;
		}
		public void setPreco(Double preco) {
			this.preco = preco;
		}

}
