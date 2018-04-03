/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.GroupeItem;
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
public class GroupeItemFacade extends AbstractFacade<GroupeItem> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupeItemFacade() {
        super(GroupeItem.class);
    }

    public void quiterGroupe(GroupeItem groupeItem) {
        groupeItem.setDateIntegration(null);
        List<Invitation> invitations = groupeItem.getGroupe().getInvitations();
        for (Invitation invitation : invitations) {
            if (invitation.getEmetteur().equals(groupeItem.getDemandeur())) {
                invitation.setDateAcceptation(null);

            }

        }

    }

}
