/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lenovo
 */
@Entity
public class ConfigSignal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long maxPublication;
    private Long minPublication;
    private Long maxUser;
    private Long minUser;

    public ConfigSignal() {
    }

    public ConfigSignal(Long id) {
        this.id = id;
    }

    public ConfigSignal(Long id, Long maxPublication, Long minPublication, Long maxUser, Long minUser) {
        this.id = id;
        this.maxPublication = maxPublication;
        this.minPublication = minPublication;
        this.maxUser = maxUser;
        this.minUser = minUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaxPublication() {
        return maxPublication;
    }

    public void setMaxPublication(Long maxPublication) {
        this.maxPublication = maxPublication;
    }

    public Long getMinPublication() {
        return minPublication;
    }

    public void setMinPublication(Long minPublication) {
        this.minPublication = minPublication;
    }

    public Long getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Long maxUser) {
        this.maxUser = maxUser;
    }

    public Long getMinUser() {
        return minUser;
    }

    public void setMinUser(Long minUser) {
        this.minUser = minUser;
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
        if (!(object instanceof ConfigSignal)) {
            return false;
        }
        ConfigSignal other = (ConfigSignal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConfigSignal{" + "id=" + id + ", maxPublication=" + maxPublication + ", minPublication=" + minPublication + ", maxUser=" + maxUser + ", minUser=" + minUser + '}';
    }

}
