package com.asgeek.books.persistence;

import com.asgeek.books.domain.config.Dto;
import com.asgeek.books.domain.repository.ConfDtoRepositoryDTO;
import com.asgeek.books.persistence.crud.ConfDtoCrudRepository;
import com.asgeek.books.persistence.mapper.ConfDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConfDtoRepository implements ConfDtoRepositoryDTO {

    @Autowired
    private ConfDtoCrudRepository confDtoCrudRepository;

    @Autowired
    private ConfDtoMapper mapper;

    @Override
    public List<Dto> getListByDtoName(String dtoName) {
        return mapper.toConfDtosDTO(confDtoCrudRepository.findByDtoName(dtoName));
    }

    @Override
    public List<Dto> getDistinctDtoNames() {
        List<Dto> dtos = new ArrayList<>();
        confDtoCrudRepository.findDistinctDtoNames().map(
                dtoNames -> {
                    for (Object[] obj : dtoNames){
                        Dto cDto = new Dto();
                        cDto.setDtoName((String) obj[0]);
                        cDto.setDtoClasName((String) obj[1]);

                        dtos.add(cDto);
                    }
                return dtoNames;
        });
        return dtos;
    }
}
