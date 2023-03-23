package com.controllers;

import com.dtos.Concertdto;
import com.dtos.Salledto;
import com.services.impl.SalleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Salle")
public class SalleController {

        @Autowired
        private SalleServiceImpl salleService;

        @GetMapping
        public List<Salledto> getSalles() {
            return salleService.getAllSalle();
        }


        @GetMapping("/{id}")
        public Salledto getSalle (@PathVariable Long id){
            return salleService.getById(id);
        }

        /**
         * Create a new Dog in the system
         */
        @PostMapping
        public Salledto saveSalle(final @RequestBody Salledto salledto){
            return salleService.save(salledto);

            // l'id est null parce que t'as  pas rempli la partie admin
        }
        /**
         * Delete a dog by it's id
         */
        @DeleteMapping("/{id}")
        public Boolean deleteSalle(@PathVariable Long id){
            return salleService.deleteSalle(id);
        }


}
