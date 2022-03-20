package com.prueba.tecnica.demo.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuario {

  @NotBlank
  private String nombreUsuario;
  @NotBlank
  private String password;


}
