package com.prueba.tecnica.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioMonedaRequest implements Serializable {

    private static final long serialVersionUID = 5438686831990398681L;

    private String monedaOrigen;
    private String monedaDestino;
    private Double monto;


}
