package com.devsuperior.bds02.servicies;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    public EventDTO update(Long id, EventDTO dto){
        try{
            Event event = repository.getOne(id);
            City city = cityRepository.getOne(dto.getCityId());
            Event newEvent = new Event(event.getId(), dto.getName(), dto.getDate(), dto.getUrl(), city);
            return new EventDTO(repository.save(newEvent));
        } catch (JpaObjectRetrievalFailureException e) {
            throw new ResourceNotFoundException("Resource not Found");
        }
    }

}
