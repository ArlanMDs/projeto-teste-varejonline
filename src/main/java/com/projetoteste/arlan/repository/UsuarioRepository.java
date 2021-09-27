package com.projetoteste.arlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoteste.arlan.entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Usuario findByNome(String nome);
}