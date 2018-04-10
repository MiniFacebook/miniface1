/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Etablissement;
import bean.EtablissementItem;
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
public class EtablissementItemFacade extends AbstractFacade<EtablissementItem> {
    
    public int creerEtablissementItem(Long id, Date dateDebut, Date dateFin, User user, Etablissement etablissement) {
        EtablissementItem etablissementItem = new EtablissementItem(id, dateDebut, dateFin);
        etablissementItem.setUser(user);
        etablissementItem.setEtablissement(etablissement);
        return 1;
    }

    public EtablissementItem clone(EtablissementItem ei) {
        EtablissementItem clone = new EtablissementItem();
        clone(ei, clone);
        return clone;
    }

    public void clone(EtablissementItem ei, EtablissementItem clone) {
        if (ei.getId() != null) {
            clone.setId(ei.getId());
        }
        clone.setEtablissement(ei.getEtablissement());
        clone.setUser(ei.getUser());
    }


    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtablissementItemFacade() {
        super(EtablissementItem.class);
    }
    
}
