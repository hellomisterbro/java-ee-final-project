package lu.uni2016.finalproject.rest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lu.uni2016.finalproject.rest.*;

/**
 * Created by kirichek on 12/10/16.
 */
@ApplicationPath("rs")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
                HelloWorldRestService.class,
                UserRestService.class,
                RideRestService.class));
    }
}