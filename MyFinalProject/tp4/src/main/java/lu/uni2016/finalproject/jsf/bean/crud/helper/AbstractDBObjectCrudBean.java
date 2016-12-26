package lu.uni2016.finalproject.jsf.bean.crud.helper;

import lu.uni2016.finalproject.ejb.entity.helper.AbstractDBObject;
import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;

import javax.enterprise.context.Conversation;
import java.io.Serializable;

/**
 * User: Schuller Tom
 */
public abstract class AbstractDBObjectCrudBean<T extends AbstractDBObject> implements Serializable {
    protected T entity;
    private boolean readOnly = true;

    public abstract Class getClazz();

    public abstract Conversation getConversation();

    public abstract AbstractDBObjectFacade getRideFacade();

    public String startNewEntity() {
        if (getConversation().isTransient()) getConversation().begin();
        try {
            entity = (T) getClazz().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        readOnly = false;
        return null;
    }

    public String startEditEntity(T editEntity) {
        if (getConversation().isTransient()) getConversation().begin();
        this.entity = editEntity;
        readOnly = false;
        return null;
    }

    public String doSaveEdit() {
        entity = (T) getRideFacade().saveOrUpdate(entity);
        endConversation();
        startNewEntity();
        return null;
    }

    public String startDelete(T toDeleteEntity) {
        if (getConversation().isTransient()) getConversation().begin();
        this.entity = toDeleteEntity;
        readOnly = true;
        return null;
    }

    public String doDelete() {
        getRideFacade().delete(entity);
        endConversation();
        return null;
    }

    public String doCancel() {
        endConversation();
        return null;
    }

    public void endConversation() {
        readOnly = true;
        if (!getConversation().isTransient()) {
            getConversation().end();
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}