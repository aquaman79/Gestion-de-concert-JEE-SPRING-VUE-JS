/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quamar
 */
@Entity
@Table(name = "salle")
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s"),
    @NamedQuery(name = "Salle.findByIdSalle", query = "SELECT s FROM Salle s WHERE s.idSalle = :idSalle"),
    @NamedQuery(name = "Salle.findByAdresse", query = "SELECT s FROM Salle s WHERE s.adresse = :adresse"),
    @NamedQuery(name = "Salle.findByCapacite", query = "SELECT s FROM Salle s WHERE s.capacite = :capacite")})
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salle")
    private Long idSalle;
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "capacite")
    private int capacite;
    @OneToMany(mappedBy = "salleIdSalle")
    private Set<Concert> concertSet;

    public Salle() {
    }

    public Salle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public Salle(Long idSalle, int capacite) {
        this.idSalle = idSalle;
        this.capacite = capacite;
    }

    public Long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Set<Concert> getConcertSet() {
        return concertSet;
    }

    public void setConcertSet(Set<Concert> concertSet) {
        this.concertSet = concertSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalle != null ? idSalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.idSalle == null && other.idSalle != null) || (this.idSalle != null && !this.idSalle.equals(other.idSalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maxence.Salle[ idSalle=" + idSalle + " ]";
    }
    
}
