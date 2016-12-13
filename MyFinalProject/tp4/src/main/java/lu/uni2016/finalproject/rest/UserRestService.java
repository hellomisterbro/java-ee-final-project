package lu.uni2016.finalproject.rest;

import lu.uni2016.finalproject.ejb.facades.UserFacade;
import lu.uni2016.finalproject.ejb.entity.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by kirichek on 12/11/16.
 */

@Path("/user")
public class UserRestService {
    @Inject
    private UserFacade userFacade;


    /*
    http://localhost:8080/tp4/rs/user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userFacade.getAll();
    }

    /*
    http://localhost:8080/tp4/rs/user/count
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public int getUsersCount() {
        return userFacade.getAll().size();
    }
}