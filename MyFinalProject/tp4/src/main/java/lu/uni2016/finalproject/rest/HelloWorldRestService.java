package lu.uni2016.finalproject.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by kirichek on 12/10/16.
 */
@Path("/helloWorld")
public class HelloWorldRestService {
    /*
    http://localhost:8080/tp4/rs/helloWorld
     */
    @GET
    @Produces("text/plain")
    public String getHelloWorld() {
        return "HelloWorld";
    }
}