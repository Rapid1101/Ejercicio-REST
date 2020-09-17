package com.ejercicios.ejercicioserviciosrest.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
	
	private String clienteId;
	private String nombreUsuario;
	private String contraseña;
	private String nombre;
	private String apellidos;
	private String correoElectronico;
	private Integer edad;
	private Double estatura;
	private Double peso;
	private Double imc;
	private Double geb;
	private Double eta;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaCreacion;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaActualizacion;

}
