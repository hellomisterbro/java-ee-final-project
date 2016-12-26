package lu.uni2016.finalproject.jsf.bean.action;

import lu.uni2016.finalproject.ejb.entity.Car;
import lu.uni2016.finalproject.ejb.entity.User;
import lu.uni2016.finalproject.ejb.facades.UserFacade;
import lu.uni2016.finalproject.jsf.bean.model.SessionData;
import lu.uni2016.finalproject.jsf.bean.model.UpdateInfoModel;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;


/**
 * Created by kirichek on 12/18/16.
 */

@Named
@ViewScoped
public class UpdateInfoAction implements Serializable {
    @Inject
    private Logger logger;
    @Inject
    private SessionData sessionData;
    @Inject
    private UpdateInfoModel updateInfoModel;
    @Inject
    private UserFacade userFacade;
    @Inject
    private UserFacade carFacase;

    public String update() {
        logger.info("settings changing... (car: " + updateInfoModel.getCar().getCarname() + " / " + updateInfoModel.getCar().getPlaces() + ")");
        User dbUser = userFacade.findUserByUsername(sessionData.getLoggedUser().getUsername());
        logger.info("dbUser: " + dbUser);
        if (dbUser != null) {
            dbUser.setCar(updateInfoModel.getCar());
            dbUser.setPassword(updateInfoModel.getPassword());
            sessionData.setLoggedUser(dbUser);
            userFacade.saveOrUpdate(dbUser);
            return "/settings.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something goes wrong.."));
        return "";
    }

    public void beginEditing() {
        User userCopy = (User) sessionData.getLoggedUser().getMyClone();
        if (userCopy.getCar() == null) {
            Car newCar = new Car();
            newCar.setPlaces(new Integer(4));
            newCar.setCarname("My New Car");
            userCopy.setCar(newCar);
        }
        updateInfoModel.setCar(userCopy.getCar());
        updateInfoModel.setPassword(userCopy.getPassword());
//        updateInfoModel.setPlacesInCar(userCopy.getCar().getPlaces());

    }

}
