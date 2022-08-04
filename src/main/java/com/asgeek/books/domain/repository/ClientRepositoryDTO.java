package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryDTO {

    List<ClientDTO> getAll();

    List<ClientDTO> searchClient(ClientDTO clientDTO);
    Optional<ClientDTO> getClient(int clientId);
    ClientDTO save(ClientDTO client);
    void delete(int clientId);

}
