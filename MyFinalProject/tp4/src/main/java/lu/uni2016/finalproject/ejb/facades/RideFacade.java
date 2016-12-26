package lu.uni2016.finalproject.ejb.facades;

import lu.uni2016.finalproject.ejb.entity.User;
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

   public List<Ride> getJoinedRidesByUser(User user) {
       return em.createQuery("select r from Ride r join r.passengers p where p.id = " +user.getId()+"").getResultList();
   }

    public List<Ride> getCreatedRidesByUser(User user){
        return em.createQuery("select r from Ride r where ('" + user.getUsername() + "'= (select username from User u where u.id = r.driver)) and r.deleted = null").getResultList();
    }

    public List<Ride> getAllRides(){
        return em.createQuery("select r from Ride r where r.deleted is null").getResultList();
    }

    public List<Ride> getRidesByLocationPoints(String start, String end){
        return em.createQuery("select r from Ride r WHERE (r.startLocation = '"+start+"' AND r.endLocation = '"+end+"')  and r.deleted = null").getResultList();
    }
}
