package lu.uni2016.finalproject.ejb.facades;

import lu.uni2016.finalproject.ejb.facades.helper.AbstractDBObjectFacade;
import lu.uni2016.finalproject.ejb.entity.Ride;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by kirichek on 12/10/16.
 */

@Named
@Stateless
public class RideFacade extends AbstractDBObjectFacade {

    List<Ride> getJoinedRidesByUser(){
        return em.createQuery("").getResultList();
    }

    List<Ride> getCreatedRidesByUser(){
        return em.createQuery("").getResultList();
    }
}
