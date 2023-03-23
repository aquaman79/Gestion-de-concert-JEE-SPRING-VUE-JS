package com.controllers;

import com.dtos.Concertdto;
import com.dtos.Groupedto;
import com.services.impl.ConcertServiceImpl;
import com.services.impl.GroupeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Groupe")
public class GroupeController {

    @Autowired
    private GroupeServiceImpl groupeService;


    @GetMapping
    public List<Groupedto> getGroupes() {
        return groupeService.getAllGroupe();
    }


}
