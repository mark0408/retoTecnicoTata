package com.prueba.tecnica.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioMonedaCrearDTO implements Serializable {

    private static final long serialVersionUID = 5438686831990398681L;

    private Long id;
    private String tipoCambio;
    private Double valorTipoCambio;
    private String nombre;

}
