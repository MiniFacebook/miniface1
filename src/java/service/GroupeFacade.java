/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Groupe;
import bean.GroupeItem;
import bean.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class GroupeFacade extends AbstractFacade<Groupe> {

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupeFacade() {
        super(Groupe.class);
    }

    public List<User> GetMembers(Groupe groupe) {

        List<User> members = new ArrayList<>();
        List<GroupeItem> groupeItems = groupe.getGroupeItems();
        for (GroupeItem groupeItem : groupeItems) {
            members.add(groupeItem.getDemandeur());
        }
        return members;
    }
}
