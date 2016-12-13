package lu.uni2016.finalproject.ejb.facades.helper;

import lu.uni2016.finalproject.ejb.entity.helper.AbstractDBObject;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by kirichek on 12/11/16.
 */

public abstract class AbstractDBObjectFacade implements Serializable {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected Logger log;

    public <T> T find(Class<T> clazz, Long id) {
        return em.find(clazz, id);
    }

    public void delete(AbstractDBObject dbObject) {
        dbObject.setDeleted(new Date());
        saveOrUpdate(dbObject);
    }

    public AbstractDBObject saveOrUpdate(AbstractDBObject dbObject) {
        return em.merge(dbObject);
    }
}
