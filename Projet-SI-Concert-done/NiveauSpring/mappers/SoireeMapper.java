package com.mappers;

import com.dtos.Concertdto;
import com.dtos.Soireedto;
import com.entities.Concert;
import com.entities.Soiree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SoireeMapper {
//    @Autowired
//    ConcertMapper concertMapper;

    public Soireedto soireeEntityToDto(Soiree soiree) {
        Soireedto soireedto = new Soireedto();
        soireedto.setIdSoiree(soiree.getIdSoiree());
        soireedto.setNomSoiree(soiree.getNomSoiree());
//       for (Concert c : soiree.getConcerts()) {
//            Concertdto cdto = this.concertMapper.concertEntitytoDTO(c);
//            soireedto.getConcerts().add(cdto);
//        }
        return soireedto;
    }


    public Soiree soireeDtoToEntity(Soireedto soireedto) {
        Soiree soiree = new Soiree();
        soiree.setNomSoiree(soireedto.getNomSoiree());
        soiree.setIdSoiree(soireedto.getIdSoiree());
//       for (Concertdto cdto : soireedto.getConcerts()) {
//            Concert c = this.concertMapper.ConcertDtoToEntity(cdto);
//            soiree.getConcerts().add(c);
//        }
        return soiree;
    }
}
