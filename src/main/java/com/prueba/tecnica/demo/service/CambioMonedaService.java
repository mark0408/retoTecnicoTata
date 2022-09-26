package com.prueba.tecnica.demo.service;


import com.prueba.tecnica.demo.domain.CambioMoneda;
import com.prueba.tecnica.demo.domain.Moneda;
import com.prueba.tecnica.demo.dto.CambioMonedaCrearDTO;
import com.prueba.tecnica.demo.dto.CambioMonedaRequest;
import com.prueba.tecnica.demo.dto.CambioMonedaResponse;
import com.prueba.tecnica.demo.exception.ValidationException;
import com.prueba.tecnica.demo.repository.CambioMonedaRepository;
import com.prueba.tecnica.demo.repository.MonedaRepository;
import com.prueba.tecnica.demo.util.CambioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class CambioMonedaService {

  @Autowired
  CambioMonedaRepository cambioMonedaRepository;
  @Autowired
  MonedaRepository monedaRepository;

  public CambioMonedaResponse obtenerResultadoCambioMoneda(CambioMonedaRequest request){
    log.info("Entro al metodo obtenerResultadoCambioMoneda");
    CambioMonedaResponse response = new CambioMonedaResponse();
    String tipoCambio = CambioUtils.crearTipoCambio(request.getMonedaOrigen(),request.getMonedaDestino());
    Optional<CambioMoneda> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(tipoCambio);
    if(optionalCambioMoneda.isPresent()){
      Double valorTipoCambio = optionalCambioMoneda.get().getValorTipoCambio();
      Double montoFinal = obtenerMontoFinal(valorTipoCambio,request.getMonto());
      response = crearCambioMonedaResponse(request,montoFinal,valorTipoCambio);
    }else{
      log.error("Error no se encuentra cambio de Moneda");
      lanzarError("M-401","Error no se encuentra cambio de Moneda");
    }
    log.info("Salio del metodo obtenerLineaMovilYOfertaPorCliente");
    return response;
  }
  private Double obtenerMontoFinal(Double valorTipoCambio, Double montoInicial){
      return valorTipoCambio*montoInicial;
  }
  private CambioMonedaResponse crearCambioMonedaResponse(CambioMonedaRequest cambioMonedaRequest,Double montoFinal, double valorTipoCambio){
    CambioMonedaResponse response = new CambioMonedaResponse();
    response.setMonto(cambioMonedaRequest.getMonto());
    response.setMontoCambiado(montoFinal);
    response.setMonedaOrigen(cambioMonedaRequest.getMonedaOrigen());
    response.setMonedaDestino(cambioMonedaRequest.getMonedaDestino());
    response.setValorTipoCambio(valorTipoCambio);
    response.setNombre("Marko Espejo");
    return response;
  }

  public CambioMonedaCrearDTO crearCambioMoneda(CambioMonedaCrearDTO request){
    CambioMonedaCrearDTO response = new CambioMonedaCrearDTO();
    CambioMoneda cambiomoneda = new CambioMoneda();
    validarRequest(request);
    Optional<CambioMoneda> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(request.getTipoCambio());
    if(optionalCambioMoneda.isPresent()){
      log.error("Ya existe un valor para ese tipo de cambio");
      lanzarError("M-402","Ya existe un valor para ese tipo de cambio");
    }else{
      cambiomoneda = cambioMonedaCrearToCambioMoneda(request);
    }
    cambiomoneda = cambioMonedaRepository.saveAndFlush(cambiomoneda);
    response = cambioMonedaToCambioMonedaCrear(cambiomoneda);
    return response;
  }
  public CambioMonedaCrearDTO actualizarCambioMoneda(CambioMonedaCrearDTO request){
    CambioMonedaCrearDTO response = new CambioMonedaCrearDTO();
    CambioMoneda cambiomoneda = new CambioMoneda();
    validarRequest(request);
    Optional<CambioMoneda> optionalCambioMoneda = cambioMonedaRepository.findByTipoCambio(request.getTipoCambio());
    if(optionalCambioMoneda.isPresent()){
      //Actualizar
      cambiomoneda = optionalCambioMoneda.get();
      cambiomoneda.setValorTipoCambio(request.getValorTipoCambio());
    }else{
      log.error("El valor del tipo de cambio no se encuentra en la bd");
      lanzarError("M-403","El valor del tipo de cambio no se encuentra en la bd");
    }
    cambiomoneda = cambioMonedaRepository.saveAndFlush(cambiomoneda);
    response = cambioMonedaToCambioMonedaCrear(cambiomoneda);
    return response;
  }
  private void validarRequest(CambioMonedaCrearDTO request){
    // Valida que moneda sea valida
    String[] monedasRequest = request.getTipoCambio().split("A");
    List<Moneda>monedasValidas = validarMonedasDeTipoCambio(monedasRequest[0],monedasRequest[1]);
    if(monedasValidas.isEmpty()){lanzarError("M-404","Moneda origen y/o moneda destino no validas");}
    //Valida casos 'euroAeuro'
    if(CambioUtils.validarMonedaRepetida(monedasRequest)){lanzarError("M-405","Moneda origen y monedas destino son iguales");}
  }
  private List<Moneda>validarMonedasDeTipoCambio(String monedaOrigen, String monedaDestino){
    List<Moneda> response = new ArrayList<>();
    Optional<Moneda> optionalMonedaOrigen = monedaRepository.findByMoneda(monedaOrigen);
    Optional<Moneda> optionalMonedaDestino = monedaRepository.findByMoneda(monedaDestino);
    if(optionalMonedaOrigen.isPresent() && optionalMonedaDestino.isPresent())
    {response.add(new Moneda());}
    return response;
  }
  private CambioMonedaCrearDTO cambioMonedaToCambioMonedaCrear(CambioMoneda cambiomoneda){
    return new CambioMonedaCrearDTO(cambiomoneda.getId(), cambiomoneda.getTipoCambio(), cambiomoneda.getValorTipoCambio(), "Marko Espejo");
  }
  private CambioMoneda cambioMonedaCrearToCambioMoneda(CambioMonedaCrearDTO cambioMoneda){
    return new CambioMoneda(null,cambioMoneda.getTipoCambio(), cambioMoneda.getValorTipoCambio());
  }

  private void lanzarError(String code,String errorMessage){
    throw new ValidationException(code,errorMessage);
  }
}
