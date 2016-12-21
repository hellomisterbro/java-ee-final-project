package lu.uni2016.finalproject.jsf.bean.model;

import lu.uni2016.finalproject.ejb.entity.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by kirichek on 12/18/16.
 */

@Named
@ViewScoped
public class UpdateInfoModel implements Serializable{

    private String password;
    private Car car;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    @PostConstruct
    void init() {
        Integer defaultPlacesInCar = 0;
        if(password == null) password = "";
        if(car == null){
            car = new Car();
            car.setPlaces(defaultPlacesInCar);
            car.setCarname("");
        }
    }
}
