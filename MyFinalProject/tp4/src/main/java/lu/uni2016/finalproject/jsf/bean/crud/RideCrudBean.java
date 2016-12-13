package lu.uni2016.finalproject.jsf.bean.crud;


import lu.uni2016.finalproject.ejb.facades.RideFacade;
import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;
import lu.uni2016.finalproject.jsf.bean.crud.helper.AbstractDBObjectCrudBean;
import lu.uni2016.finalproject.ejb.entity.Ride;

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
    private RideFacade facade;

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

    @PostConstruct
    void init(){
     super.startNewEntity();
    }
}
