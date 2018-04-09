/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Groupe;
import bean.Publication;
import bean.User;
import java.util.ArrayList;
import java.util.Date;
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
    
    
    public void savePostFil(Publication publication,User user){
        publication.setDatePublication(new Date());
        publication.setEmetteur(user);
        publication.setRecepteur(user);
        create(publication);
    }
    
    public List<Publication> mesPublications(User connectedUser){
        List<Publication> res= new ArrayList();
        String qry ="SELECT p FROM Publication p WHERE p.emmetteur.login='"+connectedUser.getLogin()+"' AND p.recepteur.login='"+connectedUser.getLogin()+"' ";
        res= em.createQuery(qry).getResultList();
        return res;
    }
    
    public List<Publication> amisPublications(User connectedUser,List<User> amis){
        List<Publication> res= new ArrayList();
        for (User ami : amis) {
            List<Publication> pubs = mesPublications(ami);
            for (Publication pub : pubs) {
                if(pub.getDroit() !=1){
                    res.add(pub);
                }
            }
        }
        return res;
    }
    public List<Publication> ProfilPublications(User connectedUser){
        List<Publication> res = new ArrayList();
        String qry="SELECT p FROM Publication p WHERE p.recepteur.login='"+connectedUser.getLogin()+"'";
        res =em.createQuery(qry).getResultList();
        return res;
    }
    public List<Publication> groupesPublications(User connectedUser, List<Groupe> mesGroupes){
        List<Publication> res= new ArrayList();
        for (Groupe groupe : mesGroupes) {
            List<Publication> pubs = groupe.getPublications();
            for (Publication pub : pubs) {
                res.add(pub);
            }
        }
        return res;
    }
    public List<Publication > findAllPub(){
        List<Publication> res = new ArrayList();
        String qry="SELECT p FROM Publication p WHERE  p.dateSuppression IS NULL AND p.dateModification IS NULL ";
        res =em.createQuery(qry).getResultList();
        return res;
    }

    

}
