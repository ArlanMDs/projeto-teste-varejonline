package com.projetoteste.arlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoteste.arlan.entity.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

}