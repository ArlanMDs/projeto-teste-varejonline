package com.projetoteste.arlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoteste.arlan.entity.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
	List<Orcamento> findByNomeCliente(String nomeCliente);

	/*
	 * 
	 * consulta personalizada. 
	

	@Query(value = "SELECT ORCAMENTO.ID, DATA_CRIACAO, ENDERECO_CLIENTE , NOME_CLIENTE, NOME_VENDEDOR , VALIDADE , \r\n"
			+ "\r\n"
			+ "SUM(SELECT PRECO_NO_PEDIDO * QTD )\r\n"
			+ "\r\n"
			+ " AS VALOR_TOTAL, COUNT(NOME_PRODUTO) AS QTD_PROD,SUM(QTD) AS QTD_PECAS, OBS\r\n"
			+ "\r\n"
			+ "FROM PEDIDO JOIN ORCAMENTO\r\n"
			+ "ON ORCAMENTO.ID = PEDIDO.ORCAMENTO_ID  \r\n"
			+ "GROUP BY ORCAMENTO.ID\r\n"
			+ "UNION\r\n"
			+ "SELECT ORCAMENTO.ID, DATA_CRIACAO, ENDERECO_CLIENTE , NOME_CLIENTE, NOME_VENDEDOR , VALIDADE , VALOR_TOTAL, QTD_PROD, QTD_PECAS, OBS\r\n"
			+ "FROM PEDIDO JOIN ORCAMENTO \r\n"
			+ "WHERE ORCAMENTO.ID NOT IN(SELECT ORCAMENTO_ID  FROM PEDIDO)", nativeQuery = true)
	List<Orcamento> getOrcamentosComTotalDosPedidos();
	 */
}