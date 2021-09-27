package com.projetoteste.arlan.servicesImplements;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.projetoteste.arlan.entity.Orcamento;
import com.projetoteste.arlan.entity.Pedido;
import com.projetoteste.arlan.repository.OrcamentoRepository;
import com.projetoteste.arlan.services.OrcamentoService;

@Service
public class OrcamentoServiceImplements implements OrcamentoService {

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@Override
	public Orcamento createOrcamento() {
		return new Orcamento();
	}

	@Override
	public Orcamento saveOrcamento(Orcamento orcamento) {
		orcamento = preparaOrcamento(orcamento, true);

		return orcamentoRepository.save(orcamento);
	}
	
	@Override
	public Orcamento editOrcamento(Orcamento orcamento) {
		orcamento = preparaOrcamento(orcamento, false);
		return orcamentoRepository.save(orcamento);
	}

	@Override
	public void addPedido(Orcamento orcamento) {
		orcamento.getPedidoList().add(new Pedido());
	}

	@Override
	public void removePedido(Orcamento orcamento, Integer pedidoIndex) {
		orcamento.getPedidoList().remove(pedidoIndex.intValue());
	}

	@Override
	public List<Orcamento> findAllOrcamentos() {
		return orcamentoRepository.findAll();
	}

	// Prepara dados antes de inserir no BD;
	private Orcamento preparaOrcamento(Orcamento orcamento, boolean save) {
		// datas e nome do vendedor não presente no editOrcamento
		// insere a data e usuário logado. //TODO implementar entidade auditable
		if(save) {
			orcamento.setNomeVendedor(SecurityContextHolder.getContext().getAuthentication().getName());
			orcamento.setDataCriacao(LocalDate.now()); 
			orcamento.setDataExpiracao(LocalDate.now().plusDays(orcamento.getValidade()));	
		}
		//
		
		
		int i = 1, qtdPecas = 0;
		double qtdValor, desconto, precoQtdDesconto, totalOrc = 0;

		for (Pedido pedido : orcamento.getPedidoList()) {
			pedido.setSequenceNumber(i);
			qtdPecas += pedido.getQtd();
			// P*Q-(P*Q*D)
			qtdValor = pedido.getPrecoUnitario() * pedido.getQtd();
			desconto = ((double) pedido.getDesconto() / 100);
			precoQtdDesconto = ((double) (qtdValor - ((double)qtdValor*desconto)));
			
			// arredondar valores para exibição e armazenamento
			precoQtdDesconto = new BigDecimal(precoQtdDesconto).setScale(2,RoundingMode.DOWN).doubleValue();
			pedido.setPrecoTotalComDesconto(precoQtdDesconto);
			totalOrc += precoQtdDesconto;
			i++;

		}

		orcamento.setQtdProd(i-1);
		orcamento.setQtdPecas(qtdPecas);
		orcamento.setValorTotal(totalOrc);
		return orcamento;
	}
}