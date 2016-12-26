package lu.uni2016.finalproject.ejb.entity;

import lu.uni2016.finalproject.ejb.entity.helper.AbstractDBObject;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kirichek on 12/10/16.
 */

@Entity
public class Ride extends AbstractDBObject{
    private User driver;
    private Set<User> passengers = new HashSet<User>();
    private String startLocation;
    private String endLocation;
    private java.util.Date dateTime;
    private Integer reservedPlaces;
    private Integer price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "places")
    public Integer getReservedPlaces() {
        return reservedPlaces;
    }

    public void setReservedPlaces(Integer places) {
        this.reservedPlaces = places;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @OneToOne
    @JoinColumn(name = "driver_id")
    public User getDriver(){
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Ride_User",
            joinColumns = { @JoinColumn(name = "ride_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    public Set<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<User> passengers) {
        this.passengers = passengers;
    }

    @Column(name = "startlocation")
    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    @Column(name = "endlocation")
    public String getEndLocation() { return endLocation; }

    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        Ride ride = (Ride) o;

        if (startLocation != null ? !startLocation.equals(ride.startLocation) : ride.startLocation != null) return false;
        if (startLocation != null ? !endLocation.equals(ride.endLocation) : ride.endLocation != null) return false;
        if (startLocation != null ? !dateTime.equals(ride.dateTime) : ride.dateTime != null) return false;
        if (startLocation != null ? !driver.equals(ride.driver) : ride.driver != null) return false;
        return passengers != null ? passengers.equals(ride.passengers) : ride.passengers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (startLocation != null ? startLocation.hashCode() : 0);
        result = 31 * result + (endLocation != null ? endLocation.hashCode() : 0);
        return result;
    }

    @Transient
    public String getListPassengers(){
        StringBuilder listPassengers = new StringBuilder();
        for (User user:passengers) {
            listPassengers.append(user.getUsername()+",");
        }
        return listPassengers.toString();
    }
}
