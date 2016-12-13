package lu.uni2016.finalproject.utils;

/**
 * Created by kirichek on 12/10/16.
 */

import lu.uni2016.finalproject.ejb.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;



/**
 * Created by kirichek on 12/10/16.
 */


@Singleton
@Startup
public class ApplicationInitializer {
    @Inject
    private Logger log;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        log.info("init....");

        if(em == null){
            log.info("IT IS NULLL BITCH");
        }
        List<User> userList = em.createQuery("select u from User u").getResultList();
            if (userList.size() == 0) {
                //database is empty, creating 'admin' user
                log.info("creating ADMIN-user...");
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword("admin1");
                adminUser.setAdminRole(true);
                em.merge(adminUser);
                log.info("ADMIN-user created.");
            }


    }
}
