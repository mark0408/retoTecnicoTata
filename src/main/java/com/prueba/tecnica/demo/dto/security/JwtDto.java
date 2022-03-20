package com.prueba.tecnica.demo.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {

  private String token;
  private String bearer = "Bearer";
  private String nombreUsuario;
  private Collection<? extends GrantedAuthority> authorities;

  public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
    this.token = token;
    this.nombreUsuario = nombreUsuario;
    this.authorities = authorities;
  }


}
