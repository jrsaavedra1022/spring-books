package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientCrudRepository extends CrudRepository<Client, Integer> {

    List<Client> findByDocument(String document);
}
