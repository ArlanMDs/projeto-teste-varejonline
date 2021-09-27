package com.projetoteste.arlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.projetoteste.arlan.entity.Perfil;
import com.projetoteste.arlan.entity.Produto;
import com.projetoteste.arlan.entity.Usuario;
import com.projetoteste.arlan.repository.PedidoRepository;
import com.projetoteste.arlan.repository.PerfilRepository;
import com.projetoteste.arlan.repository.ProdutoRepository;
import com.projetoteste.arlan.repository.UsuarioRepository;

import java.util.Arrays;

@Component
public class InjetarDadosBD implements CommandLineRunner {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PerfilRepository perfilRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		perfilRepository.save(new Perfil("GERENTE"));
		perfilRepository.save(new Perfil("VENDEDOR"));

		Perfil perfilGerente = perfilRepository.findByPerfil("GERENTE");
		Perfil perfilVendedor = perfilRepository.findByPerfil("VENDEDOR");

		Usuario usuario = new Usuario(passwordEncoder.encode("gerente"), true, "gerente1");
		usuario.setPerfis(Arrays.asList(perfilGerente));
		usuarioRepository.save(usuario);

		usuario = new Usuario(passwordEncoder.encode("vendedor"), true, "vendedor1");
		usuario.setPerfis(Arrays.asList(perfilVendedor));
		usuarioRepository.save(usuario);

		produtoRepository.save(new Produto("mouse tipo 1", 15, true));
		produtoRepository.save(new Produto("teclado tipo 1", 20, true));
		produtoRepository.save(new Produto("mouse tipo 2", 300, true));
		produtoRepository.save(new Produto("teclado tipo 2", 400, true));
		//pedidoRepository.save(new Pedido("dummy", 0, 0,0,1));
		
	}
}