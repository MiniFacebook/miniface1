/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Emploi;
import bean.EmploiItem;
import bean.User;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class EmploiItemFacade extends AbstractFacade<EmploiItem> {

    public int creerEmploiItem(Long id, Date dateDebut, Date dateFin, User user, Emploi emploi) {
        EmploiItem emploiItem = new EmploiItem(id, dateDebut, dateFin);
        emploiItem.setEmploi(emploi);
        emploiItem.setUser(user);
        create(emploiItem);
        return 1;
    }

    public EmploiItem clone(EmploiItem emploiItem) {
        EmploiItem clone = new EmploiItem();
        clone(emploiItem, clone);
        return clone;
    }

    public void clone(EmploiItem emploiItem, EmploiItem clone) {
        if (emploiItem.getId() != null) {
            clone.setId(emploiItem.getId());
        }
        clone.setEmploi(emploiItem.getEmploi());
        clone.setUser(emploiItem.getUser());
    }

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmploiItemFacade() {
        super(EmploiItem.class);
    }

}
