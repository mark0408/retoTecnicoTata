package com.prueba.tecnica.demo.exception;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

  private String code;

  public ValidationException(String code, String message){
    super(message);
    this.code=code;
  }

}
