package com.projetoteste.arlan.servicesImplements;

import com.projetoteste.arlan.entity.Perfil;
import com.projetoteste.arlan.entity.Usuario;
import com.projetoteste.arlan.repository.UsuarioRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UsuarioServiceImplements implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome_usuario) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepository.findByNome(nome_usuario);
            if(usuario==null){
                return null;
            }
            return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(), getAuthories(usuario));
        }

        catch (Exception e)
        {
         throw new UsernameNotFoundException("User not found!");
        }
    }

    private Set<GrantedAuthority> getAuthories(Usuario usuario){

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Perfil perfil: usuario.getPerfis()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perfil.getPerfil());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}