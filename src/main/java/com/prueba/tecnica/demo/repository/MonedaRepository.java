package com.prueba.tecnica.demo.repository;

import com.prueba.tecnica.demo.domain.CambioMoneda;
import com.prueba.tecnica.demo.domain.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Long> {
  List<Moneda> findByMonedaIn(String[] monedas);
  Optional<Moneda> findByMoneda(String moneda);
}
