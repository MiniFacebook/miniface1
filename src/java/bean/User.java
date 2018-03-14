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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class User implements Serializable {

   

    

    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private String sexe;
    private Boolean active;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private Double timer;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuppression;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateModification;
    private Long code; 
    private Boolean stop;
    private Boolean adminatrateur;
    private Boolean droit;
    @ManyToOne
    private Lieu lieu;
    @OneToMany(mappedBy = "concerne")
    private List<NotificationItem> notificationItems;
    @OneToMany(mappedBy = "liker")
    private List<Aime> aimes;
    @OneToMany(mappedBy = "user")
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "demandeur")
    private List<GroupeItem> groupeItems;
    @OneToMany(mappedBy = "recepteur")
    private List<MessageItem> messageItems;
    @OneToMany(mappedBy = "emetteur")
    private List<Message> messages;
    @OneToMany(mappedBy = "recepteur")
    private List<Publication> publications;
    @OneToMany(mappedBy = "user")
    private List<Photo> photos;
    @OneToMany(mappedBy = "recepteur")
    private List<Invitation> invitations;
    @OneToMany(mappedBy = "bloque")
    private List<Blocage> blocages;
    @OneToMany(mappedBy = "user")
    private List<EtablissementItem> etablissementItems;
    @OneToMany(mappedBy = "user")
    private List<EmploiItem> emploiItems;
    @OneToMany(mappedBy = "admin")
    private List<GroupeAdmin> groupeAdmins;
    @OneToMany(mappedBy = "user")
    private List<Videos> videoss;
    @OneToMany(mappedBy = "userAction")
    private List<SignalUser> signalUsers;
    @OneToMany(mappedBy = "userAction")
    private List<SignalPublication> signalPublications;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Double getTimer() {
        return timer;
    }

    public void setTimer(Double timer) {
        this.timer = timer;
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

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public Boolean getAdminatrateur() {
        return adminatrateur;
    }

    public void setAdminatrateur(Boolean adminatrateur) {
        this.adminatrateur = adminatrateur;
    }

    public Boolean getDroit() {
        return droit;
    }

    public void setDroit(Boolean droit) {
        this.droit = droit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public List<NotificationItem> getNotificationItems() {
        return notificationItems;
    }

    public void setNotificationItems(List<NotificationItem> notificationItems) {
        this.notificationItems = notificationItems;
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

    public List<GroupeItem> getGroupeItems() {
        return groupeItems;
    }

    public void setGroupeItems(List<GroupeItem> groupeItems) {
        this.groupeItems = groupeItems;
    }

    public List<MessageItem> getMessageItems() {
        return messageItems;
    }

    public void setMessageItems(List<MessageItem> messageItems) {
        this.messageItems = messageItems;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public List<Blocage> getBlocages() {
        return blocages;
    }

    public void setBlocages(List<Blocage> blocages) {
        this.blocages = blocages;
    }

    public List<EtablissementItem> getEtablissementItems() {
        return etablissementItems;
    }

    public void setEtablissementItems(List<EtablissementItem> etablissementItems) {
        this.etablissementItems = etablissementItems;
    }

    public List<EmploiItem> getEmploiItems() {
        return emploiItems;
    }

    public void setEmploiItems(List<EmploiItem> emploiItems) {
        this.emploiItems = emploiItems;
    }

    public List<GroupeAdmin> getGroupeAdmins() {
        return groupeAdmins;
    }

    public void setGroupeAdmins(List<GroupeAdmin> groupeAdmins) {
        this.groupeAdmins = groupeAdmins;
    }

    public List<Videos> getVideoss() {
        return videoss;
    }

    public void setVideoss(List<Videos> videoss) {
        this.videoss = videoss;
    }

    public List<SignalUser> getSignalUsers() {
        return signalUsers;
    }

    public void setSignalUsers(List<SignalUser> signalUsers) {
        this.signalUsers = signalUsers;
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
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the login fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", timer=" + timer + ", dateSuppression=" + dateSuppression + ", dateModification=" + dateModification + ", code=" + code + ", returnBack=" + stop + ", adminatrateur=" + adminatrateur + ", manager=" +", droit"+droit +", active"+active+ '}';
    }

    

   

   
    
}
