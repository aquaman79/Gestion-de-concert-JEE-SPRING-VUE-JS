package com.mappers;


import com.dtos.Groupedto;
import com.dtos.Salledto;
import com.entities.Groupe;
import com.entities.Salle;
import org.springframework.stereotype.Component;

@Component

public class GroupeMapper {
    public Groupedto groupeEntityToDto(Groupe groupe) {
        Groupedto groupedto = new Groupedto();
        groupedto.setNom_groupe(groupe.getNom_groupe());
        groupedto.setDescription(groupe.getDescription());
        groupedto.setId_groupe(groupe.getId_groupe());
        return groupedto;
    }


    public Groupe groupeDtoToEntity( Groupedto groupedto) {
        Groupe groupe = new Groupe();
       groupe.setNom_groupe(groupedto.getNom_groupe());
       groupe.setDescription(groupedto.getDescription());
       groupe.setId_groupe(groupedto.getId_groupe());
        return groupe;
    }
}
