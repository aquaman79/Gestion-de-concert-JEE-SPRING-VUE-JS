package com.services.impl;

import com.dtos.Concertdto;
import com.entities.Concert;
import com.entities.Groupe;
import com.entities.Salle;
import com.entities.Soiree;
import com.mappers.ConcertMapper;
import com.repositories.ConcertRepository;
import com.repositories.GroupeRepository;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("ConcertService")

public class ConcertServiceImpl {

        @Autowired
       private  ConcertMapper concertMapper;
        @Autowired

    private SalleRepository  salleRepository;
        @Autowired
        private ConcertRepository concertRepository ;
        @Autowired

        private SoireeRepository soireeRepository;
        @Autowired
        private GroupeRepository groupeRepository;
        public Concertdto saveConcert(Concertdto concertdto) {
            Concert concert  = concertMapper.ConcertDtoToEntity(concertdto);
           if(this.verifyGroupe(concertdto.getGroupe().getId_groupe(),concert)==true) {
               this.concertMapper.saveGroupeintoConcert(concert,concertdto.getGroupe().getId_groupe());
                concert = concertRepository.save(concert);
                return this.concertMapper.concertEntitytoDTO(concert);
            }
            return null;
        }

        public boolean verifyGroupe(Long idGroupe,Concert concert){
            Groupe groupe = this.groupeRepository.findById(idGroupe).orElseThrow(() -> new EntityNotFoundException("groupe not found"));
            return verifConcert(groupe,concert.getDate(),concert.getDuree());
        }
    public boolean verifConcert(Groupe groupe, Date dateConcert, int dureeConcert) {
        System.out.println("taille de concert groupe "+groupe.getConcerts().size());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        for (Concert concert : groupe.getConcerts()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate1 = sdf1.format(concert.getDate());
            String formattedDate2 = sdf1.format(dateConcert);
            if (formattedDate1.equals(formattedDate2)) {
       //         long debutConcert = concert.getDate().getTime();
//                System.out.println("hadi hya1 "+debutConcert);
     //           long finConcert = debutConcert + (concert.getDuree() * 60*1000); // conversion en millisecondes
//                System.out.println("fin concert ancien "+finConcert);
   //             long debutNouveauConcert = dateConcert.getTime();
//                System.out.println("debut nouveau "+debutNouveauConcert);
 //               long finNouveauConcert = debutNouveauConcert + (dureeConcert * 60*1000); // conversion en millisecondes
//                System.out.println("hadi hya4 "+finNouveauConcert);
//
//                if ((debutNouveauConcert >=  finConcert)) {
//                    return true;
//                }
                System.out.println("je suis ici");
                return false;
            }
        }
        return true;
    }


    public List<Concertdto> getAllConcert(){
            List<Concertdto> Concertdto = new ArrayList<>();
            List<Concert> concerts = concertRepository.findAll();
            concerts.forEach(concert -> {
                Concertdto.add(this.concertMapper.concertEntitytoDTO(concert));
            });
            return Concertdto;
        }

        public Concertdto getConcertById(Long Id) {
            Concert concert  = concertRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("concert not found"));
            return concertMapper.concertEntitytoDTO(concert);
        }

        public boolean deleteConcert(Long Id) {
            concertRepository.deleteById(Id);
            return true;
        }

        public Concertdto update(Concertdto concertdto,Long id ){
            Concert concert = this.concertRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("concert not found"));
            Salle salle  = this.salleRepository.findById(concertdto.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
            Soiree soiree =  this.soireeRepository.findById(concertdto.getSoiree().getIdSoiree()).orElseThrow(() -> new EntityNotFoundException("soiree not found"));
            Groupe groupe = this.groupeRepository.findById(concertdto.getGroupe().getId_groupe()).orElseThrow(()->new EntityNotFoundException("groupe n'existe pas"));
            concert.setSalle(salle);
            concert.setSoiree(soiree);
            concert.setGroupe(groupe);
            concert.setPrix(concertdto.getPrix());
            concert.setDate(concertdto.getDate());
            concert.setDuree(concertdto.getDuree());
            this.concertRepository.save(concert);
            return this.concertMapper.concertEntitytoDTO(concert);
        }


}
