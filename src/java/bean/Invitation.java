/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Boolean retirer;
    private Boolean rejeter;
    private Boolean amisProche;
    private Boolean vu;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEnvoi;
    private Boolean Bloquer;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAcceptation;
    @ManyToOne
    private User recepteur;
    @OneToOne
    private User emetteur;

    @ManyToOne
    private Groupe groupe;

    public Boolean getBloquer() {
        return Bloquer;
    }

    public void setBloquer(Boolean Bloquer) {
        this.Bloquer = Bloquer;
    }

    public Boolean getRejeter() {
        return rejeter;
    }

    public void setRejeter(Boolean rejeter) {
        this.rejeter = rejeter;
    }

    public Boolean getAmisProche() {
        return amisProche;
    }

    public void setAmisProche(Boolean amisProche) {
        this.amisProche = amisProche;
    }

    public Boolean getRetirer() {
        return retirer;
    }

    public void setRetirer(Boolean retirer) {
        this.retirer = retirer;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Invitation() {
    }

    public Invitation(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Date getDateAcceptation() {
        return dateAcceptation;
    }

    public void setDateAcceptation(Date dateAcceptation) {
        this.dateAcceptation = dateAcceptation;
    }

    public User getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(User recepteur) {
        this.recepteur = recepteur;
    }

    public User getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(User emetteur) {
        this.emetteur = emetteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVu() {
        return vu;
    }

    public void setVu(Boolean vu) {
        this.vu = vu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id + ", type=" + type + ", dateEnvoi=" + dateEnvoi + ", dateAcceptation=" + dateAcceptation + '}';
    }

}
