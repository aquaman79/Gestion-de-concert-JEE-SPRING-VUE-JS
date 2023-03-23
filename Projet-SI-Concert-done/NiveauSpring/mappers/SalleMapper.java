package com.mappers;

import com.dtos.Concertdto;
import com.dtos.Salledto;
import com.entities.Concert;
import com.entities.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalleMapper {

//    @Autowired
//    ConcertMapper concertMapper;

    public Salledto salleEntityToDto(Salle salle) {
        Salledto salledto = new Salledto();
        salledto.setAdresse(salle.getAdresse());
        salledto.setCapacite(salle.getCapacite());
        salledto.setIdSalle(salle.getIdSalle());
        return salledto;
    }


    public Salle salleDtoToEntity(Salledto salledto) {
        Salle salle = new Salle();
        salle.setAdresse(salledto.getAdresse());
        salle.setCapacite(salledto.getCapacite());
        salle.setIdSalle(salledto.getIdSalle());
        return salle;
    }

}
