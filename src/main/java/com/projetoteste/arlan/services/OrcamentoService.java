package com.projetoteste.arlan.services;

import java.util.List;

import com.projetoteste.arlan.entity.Orcamento;


public interface OrcamentoService {
    Orcamento createOrcamento();
    Orcamento saveOrcamento(Orcamento person);
    Orcamento editOrcamento(Orcamento person);
    void addPedido(Orcamento person);
    void removePedido(Orcamento person, Integer contactIndex);
    List<Orcamento> findAllOrcamentos();
}
