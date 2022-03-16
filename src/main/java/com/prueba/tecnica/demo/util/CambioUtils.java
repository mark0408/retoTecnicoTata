package com.prueba.tecnica.demo.util;

public class CambioUtils {
  private CambioUtils(){
  }

  public static String crearTipoCambio(String monedaOrigen, String monedaDestino){
    return monedaOrigen+"A"+monedaDestino;
  }

}
