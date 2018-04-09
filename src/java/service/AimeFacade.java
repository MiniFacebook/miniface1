/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Aime;
import bean.Publication;
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
public class AimeFacade extends AbstractFacade<Aime> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AimeFacade() {
        super(Aime.class);
    }
    
    
    public void saveLike(Publication publication){
        Aime aime= new Aime();
        aime.setPublication(publication);
        aime.setDateAime(new Date());
        aime.setLiker(new User("safia"));
        create(aime);
    }

}
