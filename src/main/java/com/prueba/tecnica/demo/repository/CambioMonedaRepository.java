package com.prueba.tecnica.demo.repository;

import com.prueba.tecnica.demo.domain.CambioMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CambioMonedaRepository extends JpaRepository<CambioMoneda, Long> {
  Optional<CambioMoneda> findByTipoCambio(String tipoCambio);
}
