/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Blocage;
import bean.Invitation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class BlocageFacade extends AbstractFacade<Blocage> {

    
    InvitationFacade invitationFacade= new InvitationFacade();
    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlocageFacade() {
        super(Blocage.class);
    }
    public void bloquer( Blocage bloquer){
	
		
	  List<Invitation> invitations=bloquer.getBloqueur().getInvitations();
	   for (Invitation invitation : invitations) {
            if (invitation.getEmetteur().equals(bloquer.getBloque())||invitation.getRecepteur().equals(bloquer.getBloque())) {
                invitation.setBloquer(true);
	               invitationFacade.edit(invitation);
            }	
	create(bloquer);
}
}
}
