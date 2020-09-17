package com.ejercicios.ejercicioserviciosrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicios.ejercicioserviciosrest.dto.ClienteDTO;
import com.ejercicios.ejercicioserviciosrest.dto.RespuestaDTO;
import com.ejercicios.ejercicioserviciosrest.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteservice;
	
	//Agregar cliente
	@PostMapping(value="/NutriNet/Cliente")
	@ResponseBody
	public ResponseEntity<RespuestaDTO> agregarNuevo(@RequestBody ClienteDTO clientedto) {
		ResponseEntity<RespuestaDTO> response;
		RespuestaDTO respuestaDTO = clienteservice.guardarCliente(clientedto);
		if(respuestaDTO.getCve_Error()<0) {
			response = ResponseEntity.status(HttpStatus.CONFLICT)
					.body(respuestaDTO);
		}
		else {
			response = ResponseEntity.status(HttpStatus.CREATED)
					.body(respuestaDTO);
		}
		return response;

	}
	
	
	//Obtener todos los clientes
	@GetMapping("/NutriNet/Cliente")
	@ResponseBody
	public ResponseEntity<RespuestaDTO>  obtenerTodos(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(clienteservice.obtenerClientes());

	}	


	//Obtener cliente especifico
	@GetMapping("/NutriNet/Cliente/{id}") 
	@ResponseBody
	public ResponseEntity<RespuestaDTO> obtenerCliente(@PathVariable String id)
	{
		ResponseEntity<RespuestaDTO> response;
		RespuestaDTO respuestaDTO = clienteservice.obtenerCliente(id);
		if(respuestaDTO.getCve_Error()<0) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(respuestaDTO);
		}
		else {
			response = ResponseEntity.status(HttpStatus.OK)
					.body(respuestaDTO);
		}
		return response;

	}


	//Actualizar cliente
	@PutMapping(value="/NutriNet/Cliente/{clienteId}")
	@ResponseBody
	public  ResponseEntity<RespuestaDTO> actualizarCliente(@PathVariable String clienteId, @RequestBody ClienteDTO clientedto) {
		ResponseEntity<RespuestaDTO> response;
		RespuestaDTO respuestaDTO = clienteservice.actualizarCliente(clienteId, clientedto);

		if(respuestaDTO.getCve_Error()==0) {
			response = ResponseEntity.status(HttpStatus.OK)
					.body(respuestaDTO);
		}
		else if(respuestaDTO.getCve_Error()==-1) {
			response = ResponseEntity.status(HttpStatus.CONFLICT)
					.body(respuestaDTO);
		}
		else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(respuestaDTO);

		}
		return response;
	}
}
