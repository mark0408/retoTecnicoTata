package com.prueba.tecnica.demo.service;

import com.prueba.tecnica.demo.domain.security.Rol;
import com.prueba.tecnica.demo.enums.RolNombre;
import com.prueba.tecnica.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

  @Autowired
  RolRepository rolRepository;

  public Optional<Rol> getByRolNombre(RolNombre rolNombre){
    return rolRepository.findByRolNombre(rolNombre);
  }

  public void save(Rol rol){
    rolRepository.save(rol);
  }
}
