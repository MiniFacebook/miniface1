/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Publication;
import bean.SignalPublication;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class SignalPublicationFacade extends AbstractFacade<SignalPublication> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SignalPublicationFacade() {
        super(SignalPublication.class);
    }
     public List<SignalPublication> publicationSignaler() {
        return getMultipleResult("SELECT s FROM SignalPublication s WHERE s.dateSupression<>'null' ORDER BY s.dateSignal");
    }

    public long nombreSignale(Publication publication) {
        return em.createQuery("SELECT s FROM SignalPublication s WHERE s.publicationSignale.id" + publication.getId() + "' ").getResultList().size();

    }
}
