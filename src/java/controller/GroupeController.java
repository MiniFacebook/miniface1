package controller;

import bean.Groupe;
import bean.Photo;
import bean.User;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.GroupeFacade;

import java.io.Serializable;
import java.util.Date;
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
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import service.UserFacade;

@Named("groupeController")
@SessionScoped
public class GroupeController implements Serializable {

    @EJB
    private service.GroupeFacade ejbFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private GroupeFacade groupeFacade;
    private List<Groupe> items = null;
    private Groupe selected;
    private List<SelectItem> types;
    private Photo photoProfil = null;
    private Photo photoBackground = null;
    private User connectedUser;

    public GroupeController() {
    }

    public Photo getPhotoBackground() {
        return photoBackground;
    }

    public void setPhotoBackground(Photo photoBackground) {
        this.photoBackground = photoBackground;
    }

    public Photo getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(Photo photoProfil) {
        this.photoProfil = photoProfil;
    }

    public Groupe getSelected() {
        if (selected == null) {
            selected = new Groupe();
        }
        return selected;
    }

    public void setSelected(Groupe selected) {
        this.selected = selected;
    }

    public User getConnectedUser() {
        connectedUser = userFacade.find(((User) SessionUtil.getAttribute("connectedUser")).getLogin());
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private GroupeFacade getFacade() {
        return ejbFacade;
    }

    public Groupe prepareCreate() {
        selected = new Groupe();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("GroupeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("GroupeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("GroupeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Groupe> getItems() {
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
                    getFacade().edit(selected);
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

    public Groupe getGroupe(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Groupe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Groupe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Groupe.class)
    public static class GroupeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GroupeController controller = (GroupeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "groupeController");
            return controller.getGroupe(getKey(value));
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
            if (object instanceof Groupe) {
                Groupe o = (Groupe) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Groupe.class.getName()});
                return null;
            }
        }

    }

    public String createGroupe() {
        int res = groupeFacade.createGroupe(selected, photoProfil, photoBackground, connectedUser);
        if (res == 1) {
            JsfUtil.addSuccessMessage("Creation avec success");

            SessionUtil.setAttribute("connectedGroupe", ejbFacade.find(selected.getId()));
            return "/template/GroupeFil";

        } else {
            JsfUtil.addErrorMessage("Echec de Creation de groupe ");
            return null;
        }

    }
}
