package com.services.impl;

import com.dtos.Salledto;
import com.entities.Salle;
import com.mappers.ConcertMapper;
import com.mappers.SalleMapper;
import com.repositories.SalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Data
@Service("SalleService")
public class SalleServiceImpl {

    @Autowired
    private ConcertMapper concertMapper;
    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private SalleMapper sallemapper;
    public Salledto save(Salledto salledto) {
        Salle salle = this.sallemapper.salleDtoToEntity(salledto);
        this.salleRepository.save(salle);
        return this.sallemapper.salleEntityToDto(salle);
    }


    public List<Salledto> getAllSalle() {
        List<Salle> salles = new ArrayList<>();
        salles = this.salleRepository.findAll();
        List<Salledto> sallesDto = new ArrayList<>();
        for (Salle salle : salles) {
            sallesDto.add(this.sallemapper.salleEntityToDto(salle));
        }
        return sallesDto;
    }


    public Salledto getById(Long id) {
        Salle salle = this.salleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("salle not found"));
        return this.sallemapper.salleEntityToDto(salle);
    }


    public boolean deleteSalle(Long Id) {
        this.salleRepository.deleteById(Id);
        return true;
    }


}
