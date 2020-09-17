package com.ejercicios.ejercicioserviciosrest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ejercicios.ejercicioserviciosrest.model.Cliente;

@Mapper
public interface ClienteMapper {
	
	ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );
	
	Cliente toEntity (ClienteDTO clienteDto);
	ClienteDTO toDTO (Cliente cliente);
	
	default List<ClienteDTO> toDTOList(List<Cliente> clienteList){
		if(clienteList==null) {
			return new ArrayList<>();
		}
		return clienteList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateClienteFromDto(ClienteDTO dto, @MappingTarget Cliente entity);
	}



