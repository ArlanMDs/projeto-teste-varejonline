package com.projetoteste.arlan.services;

import java.util.List;

import com.projetoteste.arlan.entity.Pedido;

public interface PedidoService {
	
	List<Pedido> getAllPedidos();
	
	Pedido getPedidoById(Long id);
	
	Pedido salvarPedido(Pedido pedido);

}