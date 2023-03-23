package com.controllers;


import com.dtos.Concertdto;
import com.services.impl.ConcertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Concert")
@CrossOrigin(origins = "http://localhost:8080")
public class ConcertController {

    @Autowired
    private ConcertServiceImpl concertService;


    @GetMapping
    public List<Concertdto> getConcerts() {
        return concertService.getAllConcert();
    }


    @GetMapping("/{id}")
    public Concertdto getConcert (@PathVariable Long id){
        return concertService.getConcertById(id);
    }

    /**
     * Create a new Dog in the system
     */
    @PostMapping
    public Concertdto saveConcert(final @RequestBody Concertdto concertdto){
       // System.out.println("je suis dto "+ concertdto);
        return concertService.saveConcert(concertdto);


        // l'id est null parce que t'as  pas rempli la partie admin
    }

    @PutMapping("/{id}")
    public Concertdto update(final @RequestBody Concertdto concertdto,@PathVariable Long id ){
        return this.concertService.update(concertdto,id);
    }
    /**
     * Delete a dog by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteConcert(@PathVariable Long id){
        return concertService.deleteConcert(id);
    }


}
