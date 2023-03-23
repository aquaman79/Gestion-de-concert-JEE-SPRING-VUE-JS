package com.services.impl;

import com.dtos.Concertdto;
import com.dtos.Salledto;
import com.dtos.Soireedto;
import com.entities.Concert;
import com.entities.Salle;
import com.entities.Soiree;
import com.mappers.ConcertMapper;
import com.mappers.SoireeMapper;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("SoireeService")
public class SoireServiceImpl  {

    @Autowired
    private SoireeRepository soireeRepository;

    @Autowired
    private ConcertMapper concertMapper;

    @Autowired
    private SoireeMapper soireeMapper;

    public Soireedto save(Soireedto soireedto) {
        Soiree soiree = this.soireeMapper.soireeDtoToEntity(soireedto);
        this.soireeRepository.save(soiree);
        return this.soireeMapper.soireeEntityToDto(soiree);
    }


    public List<Soireedto> getAllSoiree() {
        List<Soiree> soirees = new ArrayList<>();
        soirees = this.soireeRepository.findAll();
        List<Soireedto> soireedtos = new ArrayList<>();
        for (Soiree soiree : soirees) {
            soireedtos.add(this.soireeMapper.soireeEntityToDto(soiree));
        }
        return soireedtos;
    }


    public Soireedto getById(Long id) {
        Soiree soiree = this.soireeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("soiree not found"));
        return this.soireeMapper.soireeEntityToDto(soiree);
    }


    public boolean deleteSalle(Long Id) {
        this.soireeRepository.deleteById(Id);
        return true;
    }

    //les mappers





}
