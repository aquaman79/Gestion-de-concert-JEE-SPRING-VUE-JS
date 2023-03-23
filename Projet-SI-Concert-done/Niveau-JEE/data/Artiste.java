/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author quamar
 */
@Entity
@Table(name = "artiste")
@NamedQueries({
    @NamedQuery(name = "Artiste.findAll", query = "SELECT a FROM Artiste a"),
    @NamedQuery(name = "Artiste.findByIdArtiste", query = "SELECT a FROM Artiste a WHERE a.idArtiste = :idArtiste"),
    @NamedQuery(name = "Artiste.findByPrenomArtiste", query = "SELECT a FROM Artiste a WHERE a.prenomArtiste = :prenomArtiste"),
    @NamedQuery(name = "Artiste.findByNomArtiste", query = "SELECT a FROM Artiste a WHERE a.nomArtiste = :nomArtiste"),
    @NamedQuery(name = "Artiste.findByVilleOrigine", query = "SELECT a FROM Artiste a WHERE a.villeOrigine = :villeOrigine"),
    @NamedQuery(name = "Artiste.findByAgeArtiste", query = "SELECT a FROM Artiste a WHERE a.ageArtiste = :ageArtiste")})
public class Artiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArtiste")
    private Integer idArtiste;
    @Column(name = "prenomArtiste")
    private String prenomArtiste;
    @Column(name = "nomArtiste")
    private String nomArtiste;
    @Column(name = "villeOrigine")
    private String villeOrigine;
    @Column(name = "ageArtiste")
    private Integer ageArtiste;
    @JoinColumn(name = "id_groupe", referencedColumnName = "id_groupe")
    @ManyToOne
    private Groupe id_groupe;

    public Artiste() {
    }

    public Artiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getPrenomArtiste() {
        return prenomArtiste;
    }

    public void setPrenomArtiste(String prenomArtiste) {
        this.prenomArtiste = prenomArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public String getVilleOrigine() {
        return villeOrigine;
    }

    public void setVilleOrigine(String villeOrigine) {
        this.villeOrigine = villeOrigine;
    }

    public Integer getAgeArtiste() {
        return ageArtiste;
    }

    public void setAgeArtiste(Integer ageArtiste) {
        this.ageArtiste = ageArtiste;
    }

    public Groupe getIdGroupe() {
        return id_groupe;
    }

    public void setIdGroupe(Groupe idGroupe) {
        this.id_groupe = idGroupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtiste != null ? idArtiste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artiste)) {
            return false;
        }
        Artiste other = (Artiste) object;
        if ((this.idArtiste == null && other.idArtiste != null) || (this.idArtiste != null && !this.idArtiste.equals(other.idArtiste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String json = "{";
        json += "\"idArtiste\":" + idArtiste + ",";
        json += "\"prenomArtiste\":\"" + prenomArtiste + "\",";
        json += "\"nomArtiste\":\"" + nomArtiste + "\",";
        json += "\"villeOrigine\":\"" + villeOrigine + "\",";
        json += "\"ageArtiste\":" + ageArtiste + ",";
        json += "\"idGroupe\":" + id_groupe.getIdGroupe();
        return json + "}";
    }
    
}
