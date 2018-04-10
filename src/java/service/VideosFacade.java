/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;
import bean.Videos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class VideosFacade extends AbstractFacade<Videos> {
    
    //cree un videos
    public int Videos(Long id, String extension, User user) {
        Videos v = new Videos(id, extension);
        v.setUser(user);
        create(v);
        return 1;
    }

    //afficher liste des videos
    public List<Videos> afficherVideos(User u) {
        List<Videos> Videoss = em.createQuery("SELECT v FROM Video v WHERE v.user.login='"+u.getLogin()+"' ").getResultList();
        return Videoss;
    }

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideosFacade() {
        super(Videos.class);
    }
    
}
