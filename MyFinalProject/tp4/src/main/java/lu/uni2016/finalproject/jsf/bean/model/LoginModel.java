package lu.uni2016.finalproject.jsf.bean.model;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Logger;
import lu.uni2016.finalproject.ejb.facades.*;

/**
 * Created by kirichek on 12/10/16.
 */

@Model
public class LoginModel {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
