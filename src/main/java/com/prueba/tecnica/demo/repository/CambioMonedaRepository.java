package com.prueba.tecnica.demo.repository;

import com.prueba.tecnica.demo.domain.CambioMonedaDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CambioMonedaRepository extends JpaRepository<CambioMonedaDomain, Long> {
  Optional<CambioMonedaDomain> findByTipoCambio(String tipoCambio);
}
