package com.ejercicios.ejercicioserviciosrest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicios.ejercicioserviciosrest.dto.ClienteDTO;
import com.ejercicios.ejercicioserviciosrest.dto.ClienteMapper;
import com.ejercicios.ejercicioserviciosrest.dto.RespuestaDTO;
import com.ejercicios.ejercicioserviciosrest.model.Cliente;
import com.ejercicios.ejercicioserviciosrest.repository.ClienteRepository;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;


	private final int OK=0;
	private final int USUARIO_O_CORREO_OCUPADOS=-1;
	private final int RECURSO_NO_ENCONTRADO=-2;


	public RespuestaDTO guardarCliente(ClienteDTO clientedto) {
		RespuestaDTO respuesta = new RespuestaDTO();

		List<Cliente> clienteExistente= 
				clienteRepository.findBynombreUsuarioOrCorreoElectronico(clientedto.getNombreUsuario(), clientedto.getCorreoElectronico());

		//Verificar que el usuario o correo no exista
		if(!clienteExistente.isEmpty()) {
			respuesta.setCve_Error(USUARIO_O_CORREO_OCUPADOS);
			respuesta.setCve_Mensaje("Nombre de usuario y/o correo electronico ya registrados");
			return respuesta;	
		}

		Cliente nuevoCliente=ClienteMapper.INSTANCE.toEntity(clientedto);
		nuevoCliente.setClienteId(generarId());
		nuevoCliente.setFechaCreacion(new Date());
		clienteRepository.save(nuevoCliente);
		respuesta.setCve_Error(OK);
		respuesta.setCve_Mensaje("Usuario registrado");
		return respuesta;
	}


	public RespuestaDTO obtenerClientes() {
		RespuestaDTO respuesta = new RespuestaDTO();
		respuesta.setCve_Error(OK);
		respuesta.setClientes(ClienteMapper.INSTANCE.toDTOList(clienteRepository.findAll()));
		respuesta.setCve_Mensaje("Clientes registrados");
		return respuesta;
	}


	public RespuestaDTO obtenerCliente(String clienteId) {
		RespuestaDTO respuesta = new RespuestaDTO();
		Optional<Cliente> cliente= clienteRepository.findById(clienteId);
		if(cliente.isPresent()) {
			List<ClienteDTO> cs = new ArrayList<>();
			cs.add(ClienteMapper.INSTANCE.toDTO(cliente.get()));
			respuesta.setClientes(cs);
			respuesta.setCve_Error(OK);
			respuesta.setCve_Mensaje("Cliente encontrado");
		}else {
			respuesta.setCve_Error(RECURSO_NO_ENCONTRADO);
			respuesta.setCve_Mensaje("Cliente no encontrado");
		}
		return respuesta;
	}


	public RespuestaDTO actualizarCliente(String clienteId, ClienteDTO clientedto) {
		RespuestaDTO respuesta = new RespuestaDTO();
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteId);

		if (clienteEncontrado.isPresent()) {
			Cliente cliente=clienteEncontrado.get();
			if (usuarioOCorreoExistente(cliente.getClienteId(),cliente.getNombreUsuario(),cliente.getCorreoElectronico())) {
				respuesta.setCve_Error(USUARIO_O_CORREO_OCUPADOS);
				respuesta.setCve_Mensaje("Nombre de usuario y/o correo electronico ya registrados");
			}else {
				ClienteMapper.INSTANCE.updateClienteFromDto(clientedto, cliente);
				cliente.setFechaActualizacion(new Date());
				clienteRepository.save(cliente);
				respuesta.setCve_Error(OK);
				respuesta.setCve_Mensaje("Cliente actualizado");
			}
		} else {
			respuesta.setCve_Error(RECURSO_NO_ENCONTRADO);
			respuesta.setCve_Mensaje("Cliente no encontrado");
		}
		return respuesta;
	}


	private String generarId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	

	public boolean usuarioOCorreoExistente(String id,  String nombreUSuario, String correElectronico) {
		System.out.println("ClienteService.usuarioOCorreoExistente()");
		boolean resultado=false;
		List<Cliente> clienteExistente= 
				clienteRepository.findBynombreUsuarioOrCorreoElectronico(nombreUSuario, correElectronico);
		if (clienteExistente.isEmpty()) {
			resultado=false;
		} else if (clienteExistente.size()==1 && clienteExistente.get(0).getClienteId().equals(id)){
			resultado=false;
		}
		else {
			System.out.println("else");
			resultado=true;
		}
		return resultado;
	}
}
