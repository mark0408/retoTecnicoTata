package com.prueba.tecnica.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioMonedaResponse implements Serializable {

  private static final long serialVersionUID = 5438686831991398681L;

  private Double monto;
  private Double montoCambiado;
  private String monedaOrigen;
  private String monedaDestino;
  private Double valorTipoCambio;
  private String nombre;
}
