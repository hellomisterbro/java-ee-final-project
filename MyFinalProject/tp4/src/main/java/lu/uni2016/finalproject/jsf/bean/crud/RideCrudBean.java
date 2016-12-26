package lu.uni2016.finalproject.jsf.bean.crud;



import lu.uni2016.finalproject.ejb.entity.User;
import lu.uni2016.finalproject.ejb.facades.RideFacade;
import lu.uni2016.finalproject.ejb.facades.UserFacade;
import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;
import lu.uni2016.finalproject.jsf.bean.crud.helper.AbstractDBObjectCrudBean;
import lu.uni2016.finalproject.ejb.entity.Ride;
import lu.uni2016.finalproject.jsf.bean.model.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by kirichek on 12/12/16.
 */

@Named
@ConversationScoped
public class RideCrudBean extends AbstractDBObjectCrudBean<Ride>{
    @Inject
    private Conversation conversation;
    @Inject
    private RideFacade rideFacade;
    @Inject
    private UserFacade userFacade;
    @Inject
    private SessionData sessionData;

    @Override
    public Class getClazz() { return Ride.class; }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    public AbstractDBObjectFacade getRideFacade() {
        return rideFacade;
    }


    public String setCurrentDriver(){
        entity.setDriver(sessionData.getLoggedUser());
        return super.doSaveEdit();
    }

    public void addCurrentPassenger(){
        if(entity.getPassengers().contains(sessionData.getLoggedUser())
                || entity.getDriver().equals(sessionData.getLoggedUser())
                || entity.getDriver().getCar().getPlaces() < 1){
            return;
        }
        entity.getPassengers().add(sessionData.getLoggedUser());
        doSaveEdit();
    }

    public void removeCurrentPassenger(){
        entity.getPassengers().remove(sessionData.getLoggedUser());
        doSaveEdit();
    }

    @PostConstruct
    void init(){
        super.startNewEntity();
    }
}
