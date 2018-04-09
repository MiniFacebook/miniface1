/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commentaire;
import bean.Publication;
import bean.User;
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
public class CommentaireFacade extends AbstractFacade<Commentaire> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentaireFacade() {
        super(Commentaire.class);
    }
    public void saveComment(Publication publication,String text){
        Commentaire commentaire = new Commentaire();
        commentaire.setPublication(publication);
        commentaire.setDateCommentaire(new Date());
        commentaire.setUser(new User("safia"));
        commentaire.setTexte(text);
        create(commentaire);
    }
    public void enrigstrer(Commentaire commentaire){
        commentaire.setDateCommentaire(new Date());
        commentaire.setUser(new User("safia"));
        commentaire.setPublication(new Publication((long)1));
        create(commentaire);
    }
    public List<Commentaire> findByPub(Publication publication){
      return em.createQuery("SELECT c FROM Commentaire c WHERE c.publication.id'" + publication.getId() + "'").getResultList();
    }

    
}
