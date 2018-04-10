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
import bean.Lieu;
import bean.User;
import controller.util.HashageUtil;
import controller.util.PdfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author lenovo
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    
    InvitationFacade invitationFacade = new InvitationFacade();

    @EJB
    EmploiItemFacade emploiItemFacade;
    @EJB
    LieuFacade lieuFacade;
    @EJB
    PublicationFacade publicationFacade;
    @EJB
    EtablissementItemFacade etablissementItemFacade;


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

    public int connecter(User user) {
        User loadedUser = find(user.getLogin());
        if (loadedUser.getLogin() == null) {
            return -1;
        } else if (!loadedUser.getPassword().equals(HashageUtil.sha256(user.getPassword()))) {
            return -2;
        } else {
            loadedUser.setActive(Boolean.TRUE);
            return 1;
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
    
    
    public int cree(User user){
        
        if (user!=null) {
            
        User u = new User();
        u.setNom(user.getNom());
        u.setPrenom(user.getPrenom());
        u.setLogin(user.getLogin());
        u.setSexe(user.getSexe());
        u.setDateNaissance(user.getDateNaissance());
        u.setPassword(HashageUtil.sha256(user.getPassword()));
        u.setActive(Boolean.TRUE);
        create(u);
        return 1;
        }
        else 
            return -1;
        
        
    }
    //pour generer un pdf (jasper)
    public void generatePdf() throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("countUser", "ana");
        PdfUtil.generatePdf(findAll(), params, "user", "/jasper/user.jasper");
    }

    

    //afficher liste des amis active
    public List<User> afficheListAmis(User user) {
        List<User> tt = em.createQuery("SELECT i FROM Invitation i WHERE i.dateAcceptation IS NOT NULL And (i.recepteur.login='" + user.getLogin() + "' OR i.emetteur.login='" + user.getLogin() + "') And (i.recepteur.active='true' And i.emetteur.active= 'true')").getResultList();
        return tt;
    }

    //afficher liste des amis bloquers
    public List<User> afficheListAmisBloquer(User user) {
        List<User> tt = em.createQuery("SELECT b.bloque FROM Blocage WHERE b.dateBlocage IS NOT NULL And b.bloqueur.login= '" + user.getLogin() + "'").getResultList();
        return tt;
    }

    //Affiche les info personelle du user
    public User clone1(User u) {
        User clone = new User();
        clone.getTelephone();
        clone.getIntro();
        clone.getDateNaissance();
        clone.getEmploiItems();
        clone.getEtablissementItems();
        clone.getLieu().getAdresse();
        clone.getLieu().getVille();
        clone.getLieu().getVilleOrigine();
        edit(clone);
        return clone;

    }

    // user (parametre)
    public User clone(User u) {
        User clone = new User();
        clone(u, clone);
        return clone;
    }

    public void clone(User u, User clone) {
        clone.setLieu(u.getLieu());
        clone.setIntro(u.getIntro());
        clone.setPrenom(u.getPrenom());
        clone.setNom(u.getNom());
        clone.setEmploiItems(u.getEmploiItems());
        clone.setTelephone(u.getTelephone());
        clone.setSexe(u.getSexe());
        clone.setDateNaissance(u.getDateNaissance());
        clone.setEtablissementItems(u.getEtablissementItems());
    }

    //recherche1
    public List<User> findByNameAndPrenom(String nom, String prenom) {
        return getEntityManager().createQuery("SELECT u FROM User u WHERE u.nom='" + nom + "' AND u.prenom='" + prenom + '"').getResultList();
    }

    //faire un recherche a partie d'un nom et d'un prénom active(recherche2)
    public List<User> findByNameAndPrenomActive(String nom, String prenom) {
        return getEntityManager().createQuery("SELECT u FROM User u WHERE u.nom='" + nom + "' AND u.prenom='" + prenom + "' AND u.active == true").getResultList();
    }

    //Enregistrer la modification des info de user
    public int fonctionParam(User user, List<EtablissementItem> etablissementItems, List<EmploiItem> emploiItems) {

        User usercreer = find(user.getLogin());

        User clone = new User();
        clone(user, clone);

        Lieu clone1 = new Lieu();
        lieuFacade.clone(user.getLieu(), clone1);

        for (EtablissementItem etablissementItem : etablissementItems) {
            EtablissementItem cloned = new EtablissementItem();

            cloned = etablissementItemFacade.clone(etablissementItem);
            etablissementItemFacade.edit(cloned);
        }

        for (EmploiItem emploiItem : emploiItems) {
            EmploiItem cloned = new EmploiItem();

            cloned = emploiItemFacade.clone(emploiItem);
            emploiItemFacade.edit(cloned);
        }

        edit(usercreer);
        return 1;

    }

    
    
}
