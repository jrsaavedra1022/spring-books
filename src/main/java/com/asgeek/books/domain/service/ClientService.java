package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.ClientDTO;
import com.asgeek.books.domain.repository.ClientRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepositoryDTO clientRepositoryDTO;

    public List<ClientDTO> getAll(){
        return clientRepositoryDTO.getAll();
    }

    public List<ClientDTO> searchClient(ClientDTO clientDTO) {
        return clientRepositoryDTO.searchClient(clientDTO);
    }
    public Optional<ClientDTO> getClient(int clientId){
        return clientRepositoryDTO.getClient(clientId);
    }

    public ClientDTO save(ClientDTO client){
        return clientRepositoryDTO.save(client);
    }

    public boolean delete(int clientId){
        return getClient(clientId).map(clientDTO -> {
            clientRepositoryDTO.delete(clientId);
            return true;
        }).orElse(false);
    }

}
