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
@Table(name = "groupe")
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByIdGroupe", query = "SELECT g FROM Groupe g WHERE g.id_groupe = :id_groupe"),
    @NamedQuery(name = "Groupe.findByDescription", query = "SELECT g FROM Groupe g WHERE g.description = :description"),
    @NamedQuery(name = "Groupe.findByNomGroupe", query = "SELECT g FROM Groupe g WHERE g.nomGroupe = :nomGroupe")})
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_groupe")
    private Long id_groupe;
    @Column(name = "description")
    private String description;
    @Column(name = "nom_groupe")
    private String nomGroupe;
    @OneToMany(mappedBy = "id_groupe")
    private Set<Artiste> artisteSet;
    @OneToMany(mappedBy = "groupeIdGroupe")
    private Set<Concert> concertSet;

    public Groupe() {
    }

    public Groupe(Long idGroupe) {
        this.id_groupe = idGroupe;
    }

    public Long getIdGroupe() {
        return id_groupe;
    }

    public void setIdGroupe(Long idGroupe) {
        this.id_groupe = idGroupe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
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
        hash += (id_groupe != null ? id_groupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.id_groupe == null && other.id_groupe != null) || (this.id_groupe != null && !this.id_groupe.equals(other.id_groupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String json = "{";
        json += "\"idGroupe\":" + id_groupe + ",";
        json += "\"nomGroupe\":\"" + nomGroupe + "\",";
        json += "\"description\":\"" + description + "\"";
        return json + "}";
    }
    
}
