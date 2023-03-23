/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author quamar
 */
@Entity
@Table(name = "concert")
@NamedQueries({
    @NamedQuery(name = "Concert.findAll", query = "SELECT c FROM Concert c"),
    @NamedQuery(name = "Concert.findByid_concert", query = "SELECT c FROM Concert c WHERE c.id_concert = :id_concert"),
    @NamedQuery(name = "Concert.findByDate", query = "SELECT c FROM Concert c WHERE c.date = :date"),
    @NamedQuery(name = "Concert.findByDuree", query = "SELECT c FROM Concert c WHERE c.duree = :duree"),
    @NamedQuery(name = "Concert.findByPrix", query = "SELECT c FROM Concert c WHERE c.prix = :prix")})
public class Concert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concert")
    private Long id_concert;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "duree")
    private int duree;
    @Basic(optional = false)
    @Column(name = "prix")
    private int prix;
    @JoinColumn(name = "groupe_id_groupe", referencedColumnName = "id_groupe")
    @ManyToOne
    private Groupe groupeIdGroupe;
    @JoinColumn(name = "salle_id_salle", referencedColumnName = "id_salle")
    @ManyToOne
    private Salle salleIdSalle;
    @JoinColumn(name = "soiree_id_soiree", referencedColumnName = "id_soiree")
    @ManyToOne
    private Soiree soireeIdSoiree;

    public Concert() {
    }

    public Concert(Long id_concert) {
        this.id_concert = id_concert;
    }

    public Concert(Long id_concert, int duree, int prix) {
        this.id_concert = id_concert;
        this.duree = duree;
        this.prix = prix;
    }

    public Long getid_concert() {
        return id_concert;
    }

    public void setid_concert(Long id_concert) {
        this.id_concert = id_concert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Groupe getGroupeIdGroupe() {
        return groupeIdGroupe;
    }

    public void setGroupeIdGroupe(Groupe groupeIdGroupe) {
        this.groupeIdGroupe = groupeIdGroupe;
    }

    public Salle getSalleIdSalle() {
        return salleIdSalle;
    }

    public void setSalleIdSalle(Salle salleIdSalle) {
        this.salleIdSalle = salleIdSalle;
    }

    public Soiree getSoireeIdSoiree() {
        return soireeIdSoiree;
    }

    public void setSoireeIdSoiree(Soiree soireeIdSoiree) {
        this.soireeIdSoiree = soireeIdSoiree;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_concert != null ? id_concert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concert)) {
            return false;
        }
        Concert other = (Concert) object;
        if ((this.id_concert == null && other.id_concert != null) || (this.id_concert != null && !this.id_concert.equals(other.id_concert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "maxence.Concert[ id_concert=" + id_concert + " ]";
    }
    
}
