package lu.uni2016.finalproject.jsf.bean.crud;


import com.sun.tools.javac.util.List;
import lu.uni2016.finalproject.ejb.entity.User;
import lu.uni2016.finalproject.ejb.facades.RideFacade;
import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;
import lu.uni2016.finalproject.jsf.bean.crud.helper.AbstractDBObjectCrudBean;
import lu.uni2016.finalproject.ejb.entity.Ride;
import lu.uni2016.finalproject.jsf.bean.model.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

/**
 * Created by kirichek on 12/12/16.
 */

@Named
@ConversationScoped
public class RideCrudBean extends AbstractDBObjectCrudBean<Ride>{
    @Inject
    private Conversation conversation;
    @Inject
    private RideFacade facade;
    @Inject
    private SessionData sessionData;

    @Override
    public Class getClazz() { return Ride.class; }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    @Override
    public AbstractDBObjectFacade getFacade() {
        return facade;
    }

    @Override
    public String doSaveEdit() {
        entity.setDriver(sessionData.getLoggedUser());
        return super.doSaveEdit();
    }

    @PostConstruct
    void init(){
        super.startNewEntity();
//        super.entity.setPassengers(new ArrayList<User>());
    }
}
