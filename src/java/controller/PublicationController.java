 package controller;

import bean.Commentaire;
import bean.Publication;
import bean.User;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;


import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.AimeFacade;
import service.CommentaireFacade;
import service.PublicationFacade;
import service.UserFacade;


@Named("publicationController")
@SessionScoped
public class PublicationController implements Serializable {

   
    @EJB
    private PublicationFacade publicationFacade;
    @EJB
    private CommentaireFacade commentaireFacade;
    @EJB
    private AimeFacade aimeFacade;
    @EJB
    private UserFacade userFacade;
    private List<Publication> items ;
    private Publication selected;
    private Publication publi;
    private String text;
    private User connectedUser;
    private CommentaireController commentaireControler;
    
    public PublicationController() {
    }
    
    
    

    public Publication getSelected() {
         if (selected == null) {
            selected = new Publication();
        }
        return selected;
    }

    public void setSelected(Publication selected) {
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationFacade getEjbFacade() {
        return publicationFacade;
    }

    public void setEjbFacade(PublicationFacade ejbFacade) {
        this.publicationFacade = ejbFacade;
    }

    public CommentaireFacade getCommentaireFacade() {
        return commentaireFacade;
    }

    public void setCommentaireFacade(CommentaireFacade commentaireFacade) {
        this.commentaireFacade = commentaireFacade;
    }

    public CommentaireController getCommentaireControler() {
        return commentaireControler;
    }

    public void setCommentaireControler(CommentaireController commentaireControler) {
        this.commentaireControler = commentaireControler;
    }

    public Publication getPubli() {
        return publi;
    }

    public void setPubli(Publication publi) {
        this.publi = publi;
    }

    public AimeFacade getAimeFacade() {
        return aimeFacade;
    }

    public void setAimeFacade(AimeFacade aimeFacade) {
        this.aimeFacade = aimeFacade;
    }

    public PublicationFacade getPublicationFacade() {
        return publicationFacade;
    }

    public void setPublicationFacade(PublicationFacade publicationFacade) {
        this.publicationFacade = publicationFacade;
    }

    public User getConnectedUser() {
       connectedUser=userFacade.find(((User) SessionUtil.getAttribute("connectedUser")).getLogin());
       return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }
    
    

    public void comment(Publication publication){
        publi = publication;
        detail(publication);
    }
    
    public void detail(Publication publication){
       selected=publication;
       selected.setCommentaires(commentaireFacade.findByPub(publication));
    }
   
    
    public String addComment(){
       commentaireFacade.saveComment(publi,text);
       text = null;
       return null;
    }
    
    public void addLike(Publication publication) {
        aimeFacade.saveLike(publication);
    }
    
    public int nbAimes(Publication publication){
        return  publication.getAimes().size();
    }

    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }

    private PublicationFacade getFacade() {
        return publicationFacade;
    }
    
    public Publication prepareCreate() {
        selected = new Publication();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PublicationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PublicationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PublicationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Publication> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if(persistAction == PersistAction.UPDATE){
                        getFacade().edit(selected);
                    }
                    else{
                        getFacade().savePostFil(selected,connectedUser);
                        selected = null;
                    }
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Publication getPublication(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Publication> getItemsAvailableSelectMany() {
        return publicationFacade.findAll();
    }

    public List<Publication> getItemsAvailableSelectOne() {
        return publicationFacade.findAll();
    }

    @FacesConverter(forClass = Publication.class)
    public static class PublicationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PublicationController controller = (PublicationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "publicationController");
            return controller.getPublication(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Publication) {
                Publication o = (Publication) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Publication.class.getName()});
                return null;
            }
        }

    }


}
