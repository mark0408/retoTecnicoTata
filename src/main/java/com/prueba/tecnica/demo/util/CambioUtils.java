package com.prueba.tecnica.demo.util;

public class CambioUtils {
  private CambioUtils(){
  }

  public static String crearTipoCambio(String monedaOrigen, String monedaDestino){
    return monedaOrigen+"A"+monedaDestino;
  }

  public static boolean validarMonedaRepetida(String[] monedas){
    return monedas[0].equals(monedas[1]);
  }

}
