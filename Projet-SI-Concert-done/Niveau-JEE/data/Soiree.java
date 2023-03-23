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
@Table(name = "soiree")
@NamedQueries({
    @NamedQuery(name = "Soiree.findAll", query = "SELECT s FROM Soiree s"),
    @NamedQuery(name = "Soiree.findByIdSoiree", query = "SELECT s FROM Soiree s WHERE s.idSoiree = :idSoiree"),
    @NamedQuery(name = "Soiree.findByNomSoiree", query = "SELECT s FROM Soiree s WHERE s.nomSoiree = :nomSoiree")})
public class Soiree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_soiree")
    private Long idSoiree;
    @Column(name = "nom_soiree")
    private String nomSoiree;
    @OneToMany(mappedBy = "soireeIdSoiree")
    private Set<Concert> concertSet;

    public Soiree() {
    }

    public Soiree(Long idSoiree) {
        this.idSoiree = idSoiree;
    }

    public Long getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(Long idSoiree) {
        this.idSoiree = idSoiree;
    }

    public String getNomSoiree() {
        return nomSoiree;
    }

    public void setNomSoiree(String nomSoiree) {
        this.nomSoiree = nomSoiree;
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
        hash += (idSoiree != null ? idSoiree.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soiree)) {
            return false;
        }
        Soiree other = (Soiree) object;
        if ((this.idSoiree == null && other.idSoiree != null) || (this.idSoiree != null && !this.idSoiree.equals(other.idSoiree))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maxence.Soiree[ idSoiree=" + idSoiree + " ]";
    }
    
}
