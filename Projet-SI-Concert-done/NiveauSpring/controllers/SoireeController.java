package com.controllers;

import com.dtos.Salledto;
import com.dtos.Soireedto;
import com.services.impl.SalleServiceImpl;
import com.services.impl.SoireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Soiree")
public class SoireeController {


    @Autowired
    private SoireServiceImpl soireService;

    @GetMapping
    public List<Soireedto> getSoiree() {
        return soireService.getAllSoiree();
    }


    @GetMapping("/{id}")
    public Soireedto getSoiree (@PathVariable Long id){
        return soireService.getById(id);
    }

    /**
     * Create a new Dog in the system
     */
    @PostMapping
    public Soireedto saveSoiree(final @RequestBody Soireedto soireedto){
        System.out.println(soireedto);
        return this.soireService.save(soireedto);
    }
    /**
     * Delete a dog by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteSoiree(@PathVariable Long id){
        return this.soireService.deleteSalle(id);
    }

}
