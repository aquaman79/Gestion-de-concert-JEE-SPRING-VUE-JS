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
@Table(name = "billet")
@NamedQueries({
    @NamedQuery(name = "Billet.findAll", query = "SELECT b FROM Billet b"),
    @NamedQuery(name = "Billet.findByIdNumero", query = "SELECT b FROM Billet b WHERE b.idNumero = :idNumero")})
public class Billet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNumero")
    private Integer idNumero;
    @JoinColumn(name = "id_concert", referencedColumnName = "id_concert")
    @ManyToOne
    private Concert id_concert;

    public Billet() {
    }

    public Billet(Integer idNumero) {
        this.idNumero = idNumero;
    }

    public Integer getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(Integer idNumero) {
        this.idNumero = idNumero;
    }

    public Concert getid_concert() {
        return id_concert;
    }

    public void setid_concert(Concert id_concert) {
        this.id_concert = id_concert;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNumero != null ? idNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billet)) {
            return false;
        }
        Billet other = (Billet) object;
        if ((this.idNumero == null && other.idNumero != null) || (this.idNumero != null && !this.idNumero.equals(other.idNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Billet[ idNumero=" + idNumero + " ]";
    }
    
}
