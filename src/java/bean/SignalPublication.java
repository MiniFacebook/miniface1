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
 * @author Safia
 */
@Entity
public class SignalPublication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cause;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSignal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSupression;
    @ManyToOne
    private User userAction;
    @ManyToOne
    private Publication publicationSignale;
    private Boolean vu;

    public SignalPublication() {
    }

    public SignalPublication(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getDateSignal() {
        return dateSignal;
    }

    public void setDateSignal(Date dateSignal) {
        this.dateSignal = dateSignal;
    }

    public Date getDateSupression() {
        return dateSupression;
    }

    public void setDateSupression(Date dateSupression) {
        this.dateSupression = dateSupression;
    }

    public User getUserAction() {
        return userAction;
    }

    public void setUserAction(User userAction) {
        this.userAction = userAction;
    }

    public Publication getPublicationSignale() {
        return publicationSignale;
    }

    public void setPublicationSignale(Publication publicationSignale) {
        this.publicationSignale = publicationSignale;
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
        if (!(object instanceof SignalPublication)) {
            return false;
        }
        SignalPublication other = (SignalPublication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SignalPublication{" + "id=" + id + ", cause=" + cause + ", dateSignal=" + dateSignal + ", dateSupression=" + dateSupression + '}';
    }

}
