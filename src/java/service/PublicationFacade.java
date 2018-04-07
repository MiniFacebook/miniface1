/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Groupe;
import bean.Publication;
import bean.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class PublicationFacade extends AbstractFacade<Publication> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicationFacade() {
        super(Publication.class);
    }
     public List<Publication> prifilPubAutre(User connectedUser) {
        String query = "SELECT p FROM Publication p  WHERE p.recepteur.login='" + connectedUser.getLogin() + "' ";
        return em.createQuery(query).getResultList();
    }
    public List<Publication> prifilPubUser(User connectedUser) {
        String query = "SELECT p FROM Publication p  WHERE p.recepteur.login='" + connectedUser.getLogin() + "' AND p.emetteur.login='" + connectedUser.getLogin() + "' ";
        return em.createQuery(query).getResultList();
    }

    public List<Publication> groupPub(Groupe groupe) {
        String query = "SELECT p FROM Publication p  WHERE p.groupe.id='" + groupe.getId() + "' ";
        return em.createQuery(query).getResultList();
    }

    
}
