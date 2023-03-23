package com.services.impl;


import com.dtos.Concertdto;
import com.dtos.Groupedto;
import com.dtos.Salledto;
import com.entities.Concert;
import com.entities.Groupe;
import com.entities.Salle;
import com.mappers.GroupeMapper;
import com.repositories.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("GroupeService")

public class GroupeServiceImpl {
    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeMapper groupeMapper;
    public List<Groupedto> getAllGroupe() {
        List<Groupe> groupes = new ArrayList<>();
        groupes = this.groupeRepository.findAll();
        List<Groupedto> groupesdto = new ArrayList<>();
        for (Groupe groupe : groupes) {
            groupesdto.add(this.groupeMapper.groupeEntityToDto(groupe));
        }
        return groupesdto;
    }
}
