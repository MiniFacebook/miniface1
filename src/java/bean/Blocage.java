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
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class Blocage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pretexte;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBlocage;
    @ManyToOne
    private User bloque;
    @ManyToOne
    private User bloqueur;
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuppression;

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public String getPretexte() {
        return pretexte;
    }

    public void setPretexte(String pretexte) {
        this.pretexte = pretexte;
    }
   

    public Blocage() {
    }

    public Blocage(Long id) {
        this.id = id;
    }

    public Date getDateBlocage() {
        return dateBlocage;
    }

    public void setDateBlocage(Date dateBlocage) {
        this.dateBlocage = dateBlocage;
    }

    public User getBloque() {
        return bloque;
    }

    public void setBloque(User bloque) {
        this.bloque = bloque;
    }

    public User getBloqueur() {
        return bloqueur;
    }

    public void setBloqueur(User bloqueur) {
        this.bloqueur = bloqueur;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Blocage)) {
            return false;
        }
        Blocage other = (Blocage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blocage{" + "id=" + id + ", dateBlocage=" + dateBlocage + ", dateSuppression=" + dateSuppression + '}';
    }

    

  
    
}
