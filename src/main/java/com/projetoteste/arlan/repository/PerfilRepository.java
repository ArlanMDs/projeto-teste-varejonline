package com.projetoteste.arlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoteste.arlan.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByPerfil(String perfil);
}