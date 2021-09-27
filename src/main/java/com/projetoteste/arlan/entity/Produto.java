package com.projetoteste.arlan.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "preco")
    private int preco;
	
    @Column(name = "disponivel")
    private boolean disponivel;
    
    public Produto() {
    	
    }
    
    public Produto(String nome, int preco, boolean disponivel) {
    	this.nome = nome;
    	this.preco = preco;
    	this.disponivel = disponivel;

    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
  
}
