package com.projetoteste.arlan.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String perfil;

    @ManyToMany(mappedBy = "perfis", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarios;

    public Perfil() {

    }

    public Perfil(String perfil){
        this.perfil = perfil;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}