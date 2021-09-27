package com.projetoteste.arlan.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column()
    private LocalDate dataCriacao;
    
    @Column()
    private LocalDate dataExpiracao;
    
	@Column()
	private String nomeVendedor;
    
    @Length(max = 120, message = "max 120 letras")
    @NotBlank (message = "Nome do Cliente não pode ficar vazio")
    private String nomeCliente;

    @Length(max = 120, message = "max 120 letras")
    @NotBlank (message = "Endereço do Cliente não pode ficar vazio")
    private String endereco;

	@Min(value = 1, message= "A validade deve ter ao menos 1 dia.")
	@Max(value = 120, message= "A validade não pode ultrapassar 120 dias.")
	@Column()
	private int validade;

	@Column()//valor total com desconto 
	private double valorTotal; // TODO BigDecimal?
	
	@Column()
	private int qtdProd;

	@Column()
	private int qtdPecas;

	
	@Column(name = "obs")
	private String obs;
    
    @Valid
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "orcamento_id")
    private List<Pedido> pedidoList = new ArrayList<>();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String nomeVendedor) {
		this.endereco = nomeVendedor;
	}

	public List<Pedido> getPedidoList() {
		return pedidoList;
	}

	public void setPedidoList(List<Pedido> pedidoList) {
		this.pedidoList = pedidoList;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public int getValidade() {
		return validade;
	}

	public void setValidade(int validade) {
		this.validade = validade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQtdProd() {
		return qtdProd;
	}

	public void setQtdProd(int qtdProd) {
		this.qtdProd = qtdProd;
	}

	public int getQtdPecas() {
		return qtdPecas;
	}

	public void setQtdPecas(int qtdPecas) {
		this.qtdPecas = qtdPecas;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	
}
