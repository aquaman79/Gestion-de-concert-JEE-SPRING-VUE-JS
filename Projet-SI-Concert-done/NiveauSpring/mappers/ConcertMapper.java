package com.mappers;

import com.dtos.Concertdto;
import com.entities.Concert;
import com.entities.Groupe;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.GroupeRepository;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;


@Component

public class ConcertMapper  {
    @Autowired
    private SalleMapper salleMapper;
    @Autowired
    private SoireeMapper soireeMapper;

    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SoireeRepository soireeRepository;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeMapper groupeMapper;

    public Concert ConcertDtoToEntity(Concertdto concertdtoDto){
        Concert concertEntit = new Concert();
        concertEntit.setDate(concertdtoDto.getDate());
        concertEntit.setDuree(concertdtoDto.getDuree());
        concertEntit.setPrix(concertdtoDto.getPrix());
        concertEntit.setId_concert(concertdtoDto.getId_concert());
        Salle salle = this.salleRepository.findById(concertdtoDto.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
        Soiree soiree = this.soireeRepository.findById(concertdtoDto.getSoiree().getIdSoiree()).orElseThrow(()->new EntityNotFoundException("soiree n'existe pas"));
       //Groupe groupe = this.groupeRepository.findById(concertdtoDto.getGroupe().getIdGroupe()).orElseThrow(()->new EntityNotFoundException("groupe n'existe pas"));
        concertEntit.setSalle(salle);
        concertEntit.setSoiree(soiree);
        //concertEntit.setGroupe(groupe);
        salle.getConcerts().add(concertEntit);
        soiree.getConcerts().add(concertEntit);
        //groupe.getConcerts().add(concertEntit);
        //est ce que c'est normale que l'argument il prend que l'id est les autres sont null ,
       // du coup je suis besoin de passer par trouver la salle by findbyid
        return concertEntit;
    }
    public void saveGroupeintoConcert(Concert concert, Long idGroupe){
        Groupe groupe = this.groupeRepository.findById(idGroupe).orElseThrow(()->new EntityNotFoundException("groupe n'existe pas"));
        concert.setGroupe(groupe);
        groupe.getConcerts().add(concert);
    }
    public Concertdto concertEntitytoDTO(Concert concert){
        Concertdto concertdto = new Concertdto();
        concertdto.setPrix(concert.getPrix());
        concertdto.setDuree(concert.getDuree());
        concertdto.setDate(concert.getDate());
        concertdto.setId_concert(concert.getId_concert());
        //System.out.println("je suis dto "+ concert.getSoiree().getNomSoiree());
        Salle salle = this.salleRepository.findById(concert.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
        Soiree soiree = this.soireeRepository.findById(concert.getSoiree().getIdSoiree()).orElseThrow(()->new EntityNotFoundException("soiree n'existe pas"));
        Groupe groupe = this.groupeRepository.findById(concert.getGroupe().getId_groupe()).orElseThrow(()->new EntityNotFoundException("groupe n'existe pas"));
        concertdto.setSoiree(this.soireeMapper.soireeEntityToDto(soiree));
        concertdto.setSalle(this.salleMapper.salleEntityToDto(salle));
        concertdto.setGroupe(this.groupeMapper.groupeEntityToDto(groupe));
        //est ce que c'est la place ideale pour stocker les concerts ?
        salle.getConcerts().add(concert);
        soiree.getConcerts().add(concert);
       groupe.getConcerts().add(concert);
        return concertdto;
    }

}
