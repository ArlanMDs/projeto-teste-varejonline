package com.projetoteste.arlan.servicesImplements;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetoteste.arlan.entity.Produto;
import com.projetoteste.arlan.repository.ProdutoRepository;
import com.projetoteste.arlan.services.ProdutoService;

@Service
public class ProdutoServiceImplements implements ProdutoService{
	
	private ProdutoRepository produtoRepository;
	
	public ProdutoServiceImplements(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	@Override
	public List<Produto> getAllProdutos(){
		return produtoRepository.findAll();
	}
}