package lu.uni2016.finalproject.jsf.bean.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import lu.uni2016.finalproject.ejb.entity.User;


/**
 * Created by kirichek on 12/11/16.
 */

@SessionScoped
@Named
public class SessionData implements Serializable {
    private User loggedUser;

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
