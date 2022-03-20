package com.prueba.tecnica.demo.controller;

import com.prueba.tecnica.demo.dto.CambioMonedaCrearDTO;
import com.prueba.tecnica.demo.dto.CambioMonedaRequest;
import com.prueba.tecnica.demo.dto.CambioMonedaResponse;
import com.prueba.tecnica.demo.service.CambioMonedaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cambioMoneda")
public class CambioMonedaController {

  @Autowired
  CambioMonedaService cambioMonedaService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/cambiar", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CambioMonedaResponse.class)))
  })
  @Operation(summary = "Devuelve el resultado del cambio de moneda", tags = {"cambioMoneda"})
  public ResponseEntity<CambioMonedaResponse> obtenerResultadoCambioMoneda(@Valid @RequestBody CambioMonedaRequest request){
    return ResponseEntity.ok(cambioMonedaService.obtenerResultadoCambioMoneda(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CambioMonedaCrearDTO.class)))
  })
  @Operation(summary = "Crear y guardar en bd el tipo de cambio de moneda", tags = {"cambioMoneda"})
  public ResponseEntity<CambioMonedaCrearDTO> crearCambioMoneda(@Valid @RequestBody CambioMonedaCrearDTO request){
    return ResponseEntity.ok(cambioMonedaService.crearCambioMoneda(request));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Respuesta Correcta",
                  content = @Content(schema = @Schema(implementation = CambioMonedaCrearDTO.class)))
  })
  @Operation(summary = "Actualizar y guardar en bd el tipo de cambio de moneda", tags = {"cambioMoneda"})
  public ResponseEntity<CambioMonedaCrearDTO> actualizarCambioMoneda(@Valid @RequestBody CambioMonedaCrearDTO request){
    return ResponseEntity.ok(cambioMonedaService.actualizarCambioMoneda(request));
  }

}
