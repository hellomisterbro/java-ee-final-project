package lu.uni2016.finalproject.jsf.bean.action;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Logger;
import lu.uni2016.finalproject.ejb.facades.*;
import lu.uni2016.finalproject.ejb.entity.*;
import lu.uni2016.finalproject.jsf.bean.model.*;


/**
 * Created by kirichek on 12/10/16.
 */

@Model
public class LoginAction {
    @Inject
    private Logger logger;
    @Inject
    private UserFacade userFacade;
    @Inject
    private LoginModel credentials;
    @Inject
    private SessionData sessionData;

    public String login() {
        logger.info("login... (user: " + credentials.getUsername() + " / " + credentials.getPassword() + ")");
        sessionData.setLoggedUser(null);
        User dbUser = userFacade.findUserByUsername(credentials.getUsername());
        logger.info("dbUser: " + dbUser);
        if (dbUser != null) {
            if (dbUser.getPassword().equals(credentials.getPassword())) {
                //login is ok;
                sessionData.setLoggedUser(dbUser);
                return "/home.xhtml?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect username or password"));
        return null;
    }

    public String logout() {
        sessionData.setLoggedUser(null);
        return "/login.xhtml?faces-redirect=true";
    }


    public String checkLogin() throws IOException {
        if(!sessionData.isLoggedIn()){
            ExternalContext ec;
            ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
            return "/login.xhtml?faces-redirect=true";
        }
        return "";
    }

}
