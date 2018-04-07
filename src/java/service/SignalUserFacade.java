/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.SignalUser;
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
public class SignalUserFacade extends AbstractFacade<SignalUser> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SignalUserFacade() {
        super(SignalUser.class);
    }
     public List<SignalUser> allSignales() {
         return getMultipleResult("SELECT s FROM SignalUser s WHERE s.dateSupression IS NOT NULL ORDER BY s.dateSignal");
    }
    public long nombreSignale(User user) {
        return em.createQuery("SELECT s FROM SignalUser s WHERE s.userSignale.login='" + user.getLogin() + "' ").getResultList().size();

    }
     public List<SignalUser> listeSignalesAdmin() {
        List<SignalUser> SignalUsers =allSignales();
        List<SignalUser> usersSignales = new ArrayList<>();
        long nbrSignale = 0;
        for (SignalUser signalUser : SignalUsers) {
            nbrSignale = nombreSignale(signalUser.getUserSignale());
            if (nbrSignale > 25) {
                signalUser.setDateSupression(new Date());
                edit(signalUser);
                 signalUser.getUserSignale().setDateSuppression(new Date());
//                edit( usersSignale.getUserSignale());
            } else {
                usersSignales.add(signalUser);
            }
        }
        return usersSignales;
    }
}
