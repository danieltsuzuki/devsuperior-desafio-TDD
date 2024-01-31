package com.devsuperior.bds02.servicies;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.servicies.exceptions.DataBaseException;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public List<CityDTO> findAll(){
        return repository.findAll(Sort.by("name")).stream().map(CityDTO::new).collect(Collectors.toList());
    }

    public CityDTO insert(CityDTO dto) {
        City city = new City();
        city.setName(dto.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }

}
