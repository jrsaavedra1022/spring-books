package com.asgeek.books.domain.service;

import com.asgeek.books.domain.config.Dto;
import com.asgeek.books.persistence.ConfDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfDtoService {

    @Autowired
    private ConfDtoRepository confDtoRepository;

    public List<Dto> getLisByDtoName(String dtoName){
        return confDtoRepository.getListByDtoName(dtoName);
    }

    public List<Dto> getDistinctDtoNames(){
        return confDtoRepository.getDistinctDtoNames();
    }
}
