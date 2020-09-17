package com.ejercicios.ejercicioserviciosrest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDTO {
	
	private int Cve_Error;
	private String Cve_Mensaje;
	private List<ClienteDTO> clientes;
	
}
