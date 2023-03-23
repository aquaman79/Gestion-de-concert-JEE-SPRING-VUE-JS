package com.dtos;

import com.entities.Concert;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class Groupedto {
    private Long id_groupe;
    private String nom_groupe;

    private String description;
    private List<Concertdto> concerts;
}
