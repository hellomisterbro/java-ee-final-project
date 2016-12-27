package lu.uni2016.finalproject.rest;

import lu.uni2016.finalproject.ejb.entity.Ride;
import lu.uni2016.finalproject.ejb.facades.RideFacade;
import lu.uni2016.finalproject.jsf.bean.crud.RideCrudBean;
import lu.uni2016.finalproject.jsf.bean.model.SessionData;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kirichek on 12/27/16.
 */

@Path("/rides")
public class RideRestService {


    @Inject
    private RideFacade rideFacade;
    @Inject
    private SessionData sessionData;
    @Inject
    private RideCrudBean rideCrudBean;


    @GET
    @Path("/allrides")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ride> getRides(
            @QueryParam("from") String from,
            @QueryParam("to") String to){if(!sessionData.isLoggedIn()){
            return null;
        }
        return rideFacade.getRidesByLocationPoints(from, to);
    }

//    localhost:8080/tp4/rs/rides/create?from=Kiev&to=Misnk&datetime=2016-12-27T07:13:32&price=23
    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String createTrackInJSON(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("datetime") String datetime,
            @QueryParam("price") String price) {

        if(!sessionData.isLoggedIn()
                || to == null
                || from == null
                || datetime == null
                || price == null){

            return "Please login and enter all the fields carefully;";
        }
        Ride ride = new Ride();
        ride.setStartLocation(from);
        ride.setEndLocation(to);
        ride.setPrice(Integer.parseInt(price));
        ride.setDriver(sessionData.getLoggedUser());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {

            Date date = formatter.parse(datetime);
            System.out.println(date);
            System.out.println(formatter.format(date));
            ride.setDateTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "Date format is wrong!";
        }

        String result = "Ride saved";
        rideFacade.saveOrUpdate(ride);
        return result;

    }

}

