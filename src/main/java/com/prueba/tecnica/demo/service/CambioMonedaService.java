package com.prueba.tecnica.demo.service;


import com.prueba.tecnica.demo.domain.CambioMonedaDomain;
import com.prueba.tecnica.demo.dto.CambioMonedaCrearDTO;
import com.prueba.tecnica.demo.dto.CambioMonedaRequest;
import com.prueba.tecnica.demo.dto.CambioMonedaResponse;
import com.prueba.tecnica.demo.repository.CambioMonedaRepository;
import com.prueba.tecnica.demo.util.CambioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@Transactional
public class CambioMonedaService {

  @Value("${contante.nombre}")
  private String constanteNombre;

  @Autowired
  CambioMonedaRepository cambioMonedaRepository;

  public CambioMonedaResponse obtenerResultadoCambioMoneda(CambioMonedaRequest request){
    log.info("Entro al metodo obtenerResultadoCambioMoneda");
    CambioMonedaResponse response = new CambioMonedaResponse();
    String tipoCambio = CambioUtils.crearTipoCambio(request.getMonedaOrigen(),request.getMonedaDestino());
    Optional<CambioMonedaDomain> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(tipoCambio);
    if(optionalCambioMoneda.isPresent()){
      Double valorTipoCambio = optionalCambioMoneda.get().getValorTipoCambio();
      Double montoFinal = calcularMontoFinal(valorTipoCambio,request.getMonto());
      response = crearCambioMonedaResponse(request,montoFinal,tipoCambio);
    }else{
      log.error("Error no se encuentra cambio de Moneda");
    }
    log.info("Salio del metodo obtenerLineaMovilYOfertaPorCliente");
    return response;
  }
  private Double calcularMontoFinal(Double valorTipoCambio, Double montoInicial){
      return valorTipoCambio*montoInicial;
  }
  private CambioMonedaResponse crearCambioMonedaResponse(CambioMonedaRequest cambioMonedaRequest,Double montoFinal, String tipoCambio){
    CambioMonedaResponse response = new CambioMonedaResponse();
    response.setMonto(cambioMonedaRequest.getMonto());
    response.setMontoCambiado(montoFinal);
    response.setMonedaOrigen(cambioMonedaRequest.getMonedaOrigen());
    response.setMonedaDestino(cambioMonedaRequest.getMonedaDestino());
    response.setTipoCambio(tipoCambio);
    response.setNombre(constanteNombre);
    return response;
  }

  public CambioMonedaCrearDTO crearCambioMoneda(CambioMonedaCrearDTO request){
    CambioMonedaCrearDTO response = new CambioMonedaCrearDTO();
    CambioMonedaDomain cambioMonedaDomain = new CambioMonedaDomain();
    Optional<CambioMonedaDomain> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(request.getTipoCambio());
    if(optionalCambioMoneda.isPresent()){
      log.error("Ya existe un valor para ese tipo de cambio");
    }else{
      //Crear
      cambioMonedaDomain = cambioMonedaCrearToCambioMoneda(request);
    }
    cambioMonedaDomain = cambioMonedaRepository.saveAndFlush(cambioMonedaDomain);
    response = cambioMonedaToCambioMonedaCrear(cambioMonedaDomain);
    return response;
  }
  public CambioMonedaCrearDTO actualizarCambioMoneda(CambioMonedaCrearDTO request){
    CambioMonedaCrearDTO response = new CambioMonedaCrearDTO();
    CambioMonedaDomain cambioMonedaDomain = new CambioMonedaDomain();
    Optional<CambioMonedaDomain> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(request.getTipoCambio());
    if(optionalCambioMoneda.isPresent()){
      //Actualizar
      cambioMonedaDomain = optionalCambioMoneda.get();
      cambioMonedaDomain.setValorTipoCambio(request.getValorTipoCambio());
    }else{
      log.error("El valor del tipo de cambio no se encuentra en la bd");
    }
    cambioMonedaDomain = cambioMonedaRepository.saveAndFlush(cambioMonedaDomain);
    response = cambioMonedaToCambioMonedaCrear(cambioMonedaDomain);
    return response;
  }
  private CambioMonedaCrearDTO cambioMonedaToCambioMonedaCrear(CambioMonedaDomain cambioMonedaDomain){
    return new CambioMonedaCrearDTO(cambioMonedaDomain.getId(), cambioMonedaDomain.getTipoCambio(), cambioMonedaDomain.getValorTipoCambio(),constanteNombre);
  }
  private CambioMonedaDomain cambioMonedaCrearToCambioMoneda(CambioMonedaCrearDTO cambioMoneda){
    return new CambioMonedaDomain(null,cambioMoneda.getTipoCambio(), cambioMoneda.getValorTipoCambio());
  }
}
