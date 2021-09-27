package com.projetoteste.arlan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O nome do produto não pode ser vazio.")
	private String nomeProduto;

    @Max(value = 100000, message = "quantidade não pode ultrapassar 100.000")
    @Min(value = 1, message = "qtd não pode ser zero")
    private int qtd;
    
    @Max(value = 100)
    @Min(value = 0)
    @Column(name = "desconto")
    private int desconto;
    
    @Min(value = 0)
    @Column(name = "precoUnitario")
    private double precoUnitario; //TODO BigDecimal?

    @Column(name="precoTotalComDesconto")
    private double precoTotalComDesconto;
    
    private Integer sequenceNumber;

    public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	
	public double getPrecoTotalComDesconto() {
		return precoTotalComDesconto;
	}

	public void setPrecoTotalComDesconto(double precoTotalComDesconto) {
		this.precoTotalComDesconto = precoTotalComDesconto;
	}
    

}