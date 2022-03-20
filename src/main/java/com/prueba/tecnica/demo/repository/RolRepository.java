package com.prueba.tecnica.demo.repository;

import com.prueba.tecnica.demo.domain.security.Rol;
import com.prueba.tecnica.demo.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
  Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
