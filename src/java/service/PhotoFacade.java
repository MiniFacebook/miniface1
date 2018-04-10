/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Photo;
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
public class PhotoFacade extends AbstractFacade<Photo> {
    
    //creer un photo
    public int Photo(Long id, String chemin, String type, Boolean profil, Boolean background, User user) {
        Photo p=new Photo(id, chemin, type, profil, background);
        p.setUser(user);
        create(p);
        return 1;
    }

    //afficher liste des photos
    public List<Photo> afficherPhoto(User u) {
        List<Photo> photos = em.createQuery("SELECT p FROM Photo p WHERE p.user.login='"+u.getLogin()+"' ").getResultList();
        return photos;
    }

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() {
        super(Photo.class);
    }
    
}
