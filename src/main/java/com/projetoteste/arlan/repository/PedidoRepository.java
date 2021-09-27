package com.projetoteste.arlan.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetoteste.arlan.entity.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long>{
	
	@Query(value="SELECT * FROM Pedido WHERE orcamento_id=:orcamentoid", nativeQuery=true)
	List<Pedido> getPedidosByOrcamentoId(Long orcamentoid);
	
}
