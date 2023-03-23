package com.dtos;

import com.entities.Concert;
import lombok.Data;

import java.util.List;

@Data
public class Salledto {


    private Long idSalle;
    private String adresse ;
    private int capacite ;
    private List<Concertdto> concerts;

}
