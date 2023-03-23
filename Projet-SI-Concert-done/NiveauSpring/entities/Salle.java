package com.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;
    private String adresse ;
    private int capacite ;
   // indique que chaque salle peut avoir plusieurs concerts associées à elle.
    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Concert> concerts;


}
