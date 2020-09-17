package com.ejercicios.ejercicioserviciosrest.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
	
	@Id
	@Column(name="cliente_ID")
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
