package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Soiree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoiree;
    private String nomSoiree ;

    // indique que chaque soiree peut avoir plusieurs concerts associées à elle.
    @OneToMany(mappedBy = "soiree", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Concert> concerts;
}
