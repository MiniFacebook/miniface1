/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Lieu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class LieuFacade extends AbstractFacade<Lieu> {
    
    public int lieu(Long id, String adresse, String ville, String villeOrigine){
        Lieu l=new Lieu(id, adresse, ville, villeOrigine);
        create(l);
        return 1;
    }

    public Lieu clone(Lieu l) {
        Lieu clone = new Lieu();
        clone(l, clone);
        return clone;
    }

    public void clone(Lieu l, Lieu clone) {
        if (l.getId() != null) {
            clone.setId(l.getId());
        }
        clone.setVille(l.getVille());
        clone.setVilleOrigine(l.getVilleOrigine());
        clone.setAdresse(l.getAdresse());
        clone.setUsers(l.getUsers());
    }


    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LieuFacade() {
        super(Lieu.class);
    }
    
}
