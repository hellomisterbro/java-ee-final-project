package lu.uni2016.finalproject.jsf.bean.model;

import lu.uni2016.finalproject.ejb.entity.User;

import javax.enterprise.inject.Model;

/**
 * Created by kirichek on 12/26/16.
 */

@Model
public class JoinModel {
    private User userToJoin;

    public User getUserToJoin() {
        return userToJoin;
    }

    public void setUserToJoin(User userToJoin) {
        this.userToJoin = userToJoin;
    }

}
