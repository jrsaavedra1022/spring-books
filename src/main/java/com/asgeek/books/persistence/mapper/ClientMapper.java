package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.ClientDTO;
import com.asgeek.books.persistence.entity.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({})
    ClientDTO toClientDTO(Client client);
    List<ClientDTO> toClientsDTO(List<Client> clients);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "payments", ignore = true)
    })
    Client toClient(ClientDTO client);
}
