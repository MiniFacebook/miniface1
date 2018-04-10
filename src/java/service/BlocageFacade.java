/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Blocage;
import bean.Invitation;
import bean.User;
import controller.util.PdfUtil;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;


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
    
    //création
    public int creerBlo(Long id, String pretexte, Date dateBlocage, User bloque, User bloqueur, Date dateSuppression) {
        Blocage b = new Blocage(id, pretexte, dateBlocage, bloque, bloqueur, dateSuppression);
        create(b);
        return 1;
    }

    //pour generer un pdf (jasper)
    public void generatePdf() throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        PdfUtil.generatePdf(findAll(), params, "blocage", "/jasper/blocage.jasper");
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
