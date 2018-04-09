/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Groupe;
import bean.GroupeItem;
import bean.Invitation;
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
public class InvitationFacade extends AbstractFacade<Invitation> {

    
        GroupeItemFacade groupeItemFacade= new GroupeItemFacade();
    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitationFacade() {
        super(Invitation.class);
    }
     
    
    //l'invetation va etre remplie en se basant sur les fields de view ***** POUR LES GROUPES PRIVER****

    private void join(Invitation invitation) {
        create(invitation);
    }

    private void accepterJoin(Invitation invitation) {
        invitation.setDateAcceptation(new Date());
        
        GroupeItem groupeItem = new GroupeItem();
        groupeItem.setDateIntegration(new Date());
        groupeItem.setDemandeur(invitation.getEmetteur());
        groupeItem.setGroupe(invitation.getGroupe());
        edit(invitation);
        groupeItemFacade.create(groupeItem);

    }

    public void invitationTypeGroupe(Invitation invitation) {
        Groupe groupe = invitation.getGroupe();
        if (groupe.getType()== "private") {//1= private
            join(invitation);

        } else {//2=public
            accepterJoin(invitation);
        }
    }

    public List<Invitation> invtationEnAttant(User user) {
        return em.createQuery("select inv from Invitation inv where inv.emetteur'" + user + "' OR inv.recepteur'" + user + "' and inv.dateAcceptation IS NULL").getResultList();

    }

public void retirerAmi(Invitation invitaion){
	invitaion.setRetirer(true);
      	edit(invitaion);
}
public void rejeterInvitation(Invitation invitaion){
	invitaion.setRejeter(true);
      	edit(invitaion);
}



 public List<Invitation> mesInvitationsRecues (User loadedUser,List<Invitation> liens){
        List<Invitation> res = new ArrayList();
        for (int i = 0; i < liens.size(); i++) {
            Invitation lien = liens.get(i);
            if(lien.getRecepteur().equals(loadedUser)&& lien.getDateAcceptation()==null){
                res.add(lien);
            }
        }
      return res;
    }
    
    
    
    public void confirmer(Invitation lien){
       lien.setDateAcceptation(new Date());
        edit(lien);
    }

}
