package com.projetoteste.arlan.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "senha")
    private String senha;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Collection<Perfil> perfis;
    
    //@OneToMany
    //@JoinColumn(name = "usuario_id")
    //private List<Orcamento> orcamentos = new ArrayList<>();
    //TODO Corrigir relações
	public Usuario(){

    }

    public Usuario(String senha, boolean ativo, String nome) {
        this.senha = senha;
        this.ativo = ativo;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.senha = passwordEncoder.encode(senha);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Collection<Perfil> perfis) {
        this.perfis = perfis;
    }
    
}