package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.ClientDTO;
import com.asgeek.books.domain.repository.ClientRepositoryDTO;
import com.asgeek.books.persistence.crud.ClientCrudRepository;
import com.asgeek.books.persistence.entity.Client;
import com.asgeek.books.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository implements ClientRepositoryDTO {

    @Autowired
    private ClientCrudRepository clientCrudRepository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<ClientDTO> getAll() {
        List<Client> clients = (List<Client>) clientCrudRepository.findAll();
        return mapper.toClientsDTO(clients);
    }

    @Override
    public List<ClientDTO> searchClient(ClientDTO clientDTO) {
        Client client = mapper.toClient(clientDTO);
        return mapper.toClientsDTO(clientCrudRepository.findByDocument(client.getDocument()));
    }

    @Override
    public Optional<ClientDTO> getClient(int clientId) {
        return clientCrudRepository.findById(clientId).map(client -> mapper.toClientDTO(client));
    }

    @Override
    public ClientDTO save(ClientDTO client) {
        Client newClient = mapper.toClient(client);
        return mapper.toClientDTO(clientCrudRepository.save(newClient));
    }

    @Override
    public void delete(int clientId) {
        clientCrudRepository.deleteById(clientId);
    }
}
