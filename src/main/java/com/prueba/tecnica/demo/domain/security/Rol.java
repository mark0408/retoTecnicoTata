package com.prueba.tecnica.demo.domain.security;

import com.prueba.tecnica.demo.enums.RolNombre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  @Enumerated(EnumType.STRING)
  private RolNombre rolNombre;

  public Rol(@NotNull RolNombre rolNombre) {
    this.rolNombre = rolNombre;
  }

}
