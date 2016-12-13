package lu.uni2016.finalproject.jsf.bean.crud;

import lu.uni2016.finalproject.ejb.entity.*;
import lu.uni2016.finalproject.ejb.facades.*;
import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;
import lu.uni2016.finalproject.jsf.bean.crud.helper.AbstractDBObjectCrudBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Schuller Tom
 */
@Named
@ConversationScoped
public class UserCrudBean extends AbstractDBObjectCrudBean<User> {
    @Inject
    private Conversation conversation;
    @Inject
    private UserFacade facade;

    @Override
    public Class getClazz() {
        return User.class;
    }

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