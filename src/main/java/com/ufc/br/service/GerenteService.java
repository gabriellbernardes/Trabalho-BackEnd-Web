package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.ImagemFileUtils;

@Service
public class GerenteService {

	@Autowired
	private PratoRepository pratoRepo;
	
	//adicionar um prato
	public void cadastraPrato(Prato prato, @RequestParam(value = "imagem") MultipartFile imagem){
		
		String caminho ="imagens/"+prato.getNome()+".png" ;
		prato.setStatus(true);
		ImagemFileUtils.salvarImagem(caminho, imagem);
		pratoRepo.save(prato);
		
	}


	//lista de todos os pratos
	public List<Prato> listarPratos() {
		List<Prato> pratos = new ArrayList<Prato>();
		
		for( Prato aux : pratoRepo.findAll() ) {	
			if( aux.isStatus() ) {
				pratos.add(aux);
			}		
		}
		
		return pratos;

	}
	
	//excluir um prato
	public void excluirPrato(Long id) {
			Prato p = pratoRepo.getOne(id);
			p.setStatus(false);
			pratoRepo.save(p);
	}
	//buscar um prato por Id
	public Prato buscarPorId(Long codigo) {
		return pratoRepo.getOne(codigo);
		
	}
	
}
