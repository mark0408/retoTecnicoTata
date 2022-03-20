package com.prueba.tecnica.demo.service;

import com.prueba.tecnica.demo.domain.security.Usuario;
import com.prueba.tecnica.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
    return usuarioRepository.findByNombreUsuario(nombreUsuario);
  }

  public boolean existsByNombreUsuario(String nombreUsuario){
    return usuarioRepository.existsByNombreUsuario(nombreUsuario);
  }

  public boolean existsByEmail(String email){
    return usuarioRepository.existsByEmail(email);
  }

  public void save(Usuario usuario){
    usuarioRepository.save(usuario);
  }
}
