package lu.uni2016.finalproject.jsf.bean.action;

import lu.uni2016.finalproject.ejb.entity.Ride;
import lu.uni2016.finalproject.ejb.facades.RideFacade;
import lu.uni2016.finalproject.jsf.bean.model.SearchModel;
import lu.uni2016.finalproject.jsf.bean.model.SessionData;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by kirichek on 12/26/16.
 */


@Named
@ViewScoped
public class JoinAction implements Serializable {
    @Inject
    private Logger logger;
    @Inject
    private RideFacade rideFacade;
    @Inject
    private SearchModel searchModel;
    @Inject
    private SessionData sessionData;

    public List<Ride > getUserCreatedRides(){
        return rideFacade.getJoinedRidesByUser(sessionData.getLoggedUser());
    }

    public void updateSearchResults() {
        if(searchModel.getStartpoint().isEmpty() || searchModel.getEndpoint().isEmpty()){
            searchModel.setSearchResult(rideFacade.getAllRides());
        } else {
            searchModel.setSearchResult(rideFacade.getRidesByLocationPoints(searchModel.getStartpoint(), searchModel.getEndpoint()));
        }
    }


}