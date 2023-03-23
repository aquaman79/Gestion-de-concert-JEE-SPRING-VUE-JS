package com.dtos;

import com.entities.Concert;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class Soireedto {
    private Long idSoiree;
    private String nomSoiree ;

    // indique que chaque soiree peut avoir plusieurs concerts associées à elle.
    private List<Concertdto> concerts;
}
