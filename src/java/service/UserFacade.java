/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Blocage;
import bean.Emploi;
import bean.EmploiItem;
import bean.Etablissement;
import bean.EtablissementItem;
import bean.Groupe;
import bean.GroupeItem;
import bean.Invitation;
import bean.SignalPublication;
import bean.SignalUser;
import bean.User;
import controler.util.HashageUtil;
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
public class UserFacade extends AbstractFacade<User> {

    InvitationFacade invitationFacade = new InvitationFacade();

    @PersistenceContext(unitName = "faceNiMiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public void saveMethode(User user) {

        create(user);

    }

    public int ceConnecter(User user) {
        if (user == null || user.getLogin() == null) {
            return -3;
        } else {
            User loadedUser = find(user.getLogin());
            if (loadedUser.getLogin() == null) {
                return -1;
            } else if (!loadedUser.getPassword().equals(HashageUtil.sha256(user.getPassword()))) {
                return -2;
            } else {
                return 1;

            }
        }
    }

    public List<Groupe> getGroupesByUser(User user) {
        List<Groupe> groupes = new ArrayList<>();
        List<GroupeItem> mesGroupesitem = user.getGroupeItems();
        for (GroupeItem groupeItem : mesGroupesitem) {
            Groupe groupe = groupeItem.getGroupe();
            groupes.add(groupe);
        }
        return groupes;

    }

    public List<User> getAmis(User user) {
        List<User> amis = new ArrayList<>();
        List<Invitation> invitations = invitationFacade.findAll();
        for (Invitation invitation : invitations) {
            if (invitation.getEmetteur().equals(user) && invitation.getDateAcceptation() != null && invitation.getRetirer() == false && invitation.getBloquer() == false) {
                User ami = invitation.getRecepteur();
                amis.add(ami);

            }
            if (invitation.getRecepteur().equals(user) && invitation.getDateAcceptation() != null && invitation.getRetirer() == false && invitation.getBloquer() == false) {
                User ami = invitation.getEmetteur();
                amis.add(ami);

            }

        }
        return amis;

    }

    public List<User> AmisDamis(User user) {
        List<User> amisSug = new ArrayList<>();

        List<User> amis = getAmis(user);
        for (User ami : amis) {
            List<Invitation> invitations = ami.getInvitations();
            for (Invitation invitation : invitations) {
                if ((invitation.getEmetteur().equals(user) || invitation.getRecepteur().equals(user)) && invitation.getAmisProche() == true && invitation.getBloquer() == false) {
                    List<User> amis_damis = getAmis(ami);
                    for (User amis_dami : amis_damis) {
                        amisSug.add(amis_dami);
                    }
                }
            }

        }
        return amisSug;
    }

    public List<User> findBloqueByUser(User user) {
        List<User> bloquerss = new ArrayList<>();
        List<Blocage> bloquers = user.getBlocages();
        for (Blocage bloquer : bloquers) {
            if (bloquer.getBloqueur().equals(user)) {
                bloquerss.add(bloquer.getBloque());
            }
        }
        return bloquerss;

    }

    public List<User> RechercheUser(String nom, List<User> noInvited) {
        List<User> res = new ArrayList();
        for (int i = 0; i < noInvited.size(); i++) {
            User user = noInvited.get(i);
            String data1 = "" + user.getNom() + "" + user.getPrenom() + "";
            String data2 = "" + user.getPrenom() + "" + user.getNom() + "";
            if (data1.startsWith(nom) || data2.startsWith(nom)) {
                res.add(user);
            }
        }
        return res;
    }

    public List<Invitation> mesInvitationsRecues(User loadedUser, List<Invitation> liens) {
        List<Invitation> res = new ArrayList();
        for (int i = 0; i < liens.size(); i++) {
            Invitation lien = liens.get(i);
            if (lien.getRecepteur().equals(loadedUser) && lien.getDateAcceptation() == null) {
                res.add(lien);
            }
        }
        return res;
    }

    public List<User> amisEtablissmentList(User connectedUser) {

        Etablissement userEtablissement = currantEtablissementForUser(connectedUser);
        List<User> list = new ArrayList<>();
        List<User> users = findAll();
        for (User user : users) {
            List<EtablissementItem> etablissementItems = user.getEtablissementItems();
            for (EtablissementItem etablissementItem : etablissementItems) {
                Etablissement Etablissement = currantEtablissementForUser(etablissementItem.getUser());

                if (Etablissement.equals(userEtablissement)) {
                    list.add(user);
                }

            }

        }
        return list;
    }

    public Etablissement currantEtablissementForUser(User connectedUser) {

        List<EtablissementItem> etablissementItems = connectedUser.getEtablissementItems();
        for (EtablissementItem etablissementItem : etablissementItems) {
            if (etablissementItem.getDateDebut() != null && etablissementItem.getDateFin() == null) {
                return etablissementItem.getEtablissement();
            }

        }

        return null;

    }

    public Emploi currantEmploiForUser(User connectedUser) {

        List<EmploiItem> EmploiItems = connectedUser.getEmploiItems();
        for (EmploiItem emploiItem : EmploiItems) {
            if (emploiItem.getDateDebut() != null && emploiItem.getDateFin() == null) {
                return emploiItem.getEmploi();
            }

        }

        return null;

    }

    public List<User> amisEnCommun(User ConnectedUser, User selectedUser) {
        List<User> res = new ArrayList();
        List<User> amis = getAmis(ConnectedUser);
        List<User> amiss = getAmis(selectedUser);
        amis.remove(selectedUser);
        amiss.remove(ConnectedUser);
        for (User ami : amis) {
            for (User amis1 : amiss) {
                if (amis1.equals(ami)) {
                    res.add(ami);
                }

            }

        }
        return res;
    }
     SignalUserFacade signalUserFacade;
    SignalPublicationFacade signalPublicationFacade;
   
    public List<SignalUser> userSignales() {
        List<SignalUser> SignalUsers = signalUserFacade.listeSignalesAdmin();
        List<SignalUser> usersSignales = new ArrayList<>();
        long nbrSignale = 0;
        for (SignalUser usersSignale : SignalUsers) {
            nbrSignale = signalUserFacade.nombreSignale(usersSignale.getUserSignale());
            if (nbrSignale > 25) {
                usersSignale.setDateSupression(new Date());
                signalUserFacade.edit(usersSignale);
                 usersSignale.getUserSignale().setDateSuppression(new Date());
                edit( usersSignale.getUserSignale());
            } else {
                usersSignales.add(usersSignale);
            }
        }
        return usersSignales;
    }
    

     public List<SignalPublication> publicationSignales() {
        List<SignalPublication> SignalPublications = signalPublicationFacade.publicationSignaler();
        List<SignalPublication> pubSignales = new ArrayList<>();
        long nbrSignale = 0;
        for (SignalPublication signalPublication : SignalPublications) {
            nbrSignale = signalPublicationFacade.nombreSignale(signalPublication.getPublicationSignale());
            if (nbrSignale > 25) {
                signalPublication.setDateSupression(new Date());
                signalPublicationFacade.edit(signalPublication);
            } else {
                pubSignales.add(signalPublication);
            }
        }
        return pubSignales;
    }
}
