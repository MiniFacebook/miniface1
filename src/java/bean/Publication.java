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
public class Publication implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    private String texte;
    @ManyToOne
    private User recepteur;
    @ManyToOne
    private User emetteur;
    @ManyToOne
    private Groupe groupe;
    private int droit;
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuppression;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateModification;
    private Long code; 
    private Boolean returnBack;
   
    @OneToMany(mappedBy = "publication")
    private List<Aime> aimes;
    @OneToMany(mappedBy = "publication")
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "publication")
    private List<Contenu> contenus;
    @OneToMany(mappedBy = "publicationSignale")
    private List<SignalPublication> signalPublications;

    public Publication() {
    }

    
    public Publication(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
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

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public int getDroit() {
        return droit;
    }

    public void setDroit(int droit) {
        this.droit = droit;
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

    public Boolean getReturnBack() {
        return returnBack;
    }

    public void setReturnBack(Boolean returnBack) {
        this.returnBack = returnBack;
    }

    public List<Aime> getAimes() {
        return aimes;
    }

    public void setAimes(List<Aime> aimes) {
        this.aimes = aimes;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Contenu> getContenus() {
        return contenus;
    }

    public void setContenus(List<Contenu> contenus) {
        this.contenus = contenus;
    }

    public List<SignalPublication> getSignalPublications() {
        return signalPublications;
    }

    public void setSignalPublications(List<SignalPublication> signalPublications) {
        this.signalPublications = signalPublications;
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
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", datePublication=" + datePublication + ", texte=" + texte + ", droit=" + droit + ", dateSuppression=" + dateSuppression + ", dateModification=" + dateModification + ", code=" + code + ", returnBack=" + returnBack + '}';
    }

   

   
    
}
