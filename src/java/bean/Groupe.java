/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class Groupe implements Serializable {
 
    @OneToMany(mappedBy = "groupe")
    private List<Publication> publications;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Long etat;
    private String type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    @ManyToOne
    private User admin;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuppression;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateModification;
    private Long code; 
    private Boolean returnBack;
    @OneToMany(mappedBy = "groupe")
    private List<Invitation> invitations;
    @OneToMany(mappedBy = "groupe")
    private List<GroupeItem> groupeItems;

    public List<GroupeItem> getGroupeItems() {
        return groupeItems;
    }

    public void setGroupeItems(List<GroupeItem> groupeItems) {
        this.groupeItems = groupeItems;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }
     
   

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Boolean getreturnBack() {
        return returnBack;
    }

    public void setreturnBack(Boolean returnBack) {
        this.returnBack = returnBack;
    }
    @OneToMany(mappedBy = "groupe")
    private List<GroupeAdmin> groupeAdmins;

    public List<GroupeAdmin> getGroupeAdmins() {
        return groupeAdmins;
    }

    public void setGroupeAdmins(List<GroupeAdmin> groupeAdmins) {
        this.groupeAdmins = groupeAdmins;
    }

    public Groupe() {
    }

    public Groupe(Long id) {
        this.id = id;
    }

  

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getEtat() {
        return etat;
    }

    public void setEtat(Long etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nom=" + nom + ", etat=" + etat + ", type=" + type + ", dateCreation=" + dateCreation + ", admin=" + admin + ", dateSuppression=" + dateSuppression + ", dateModification=" + dateModification + ", code=" + code + ", returnBack=" + returnBack + '}';
    }



    
}
