package com.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_concert;
    private Date date;
    private int duree;
    private int prix ;
    //un concert ne peut avoir qu'une seul soiree mais une soiree peut avoir plusieurs concerts.
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Soiree soiree;
    @ManyToOne
    private Groupe groupe;
}
