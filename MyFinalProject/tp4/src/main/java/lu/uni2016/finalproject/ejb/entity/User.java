package lu.uni2016.finalproject.ejb.entity;

import lu.uni2016.finalproject.ejb.entity.helper.AbstractDBObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kirichek on 12/10/16.
 */
@Entity
public class User extends AbstractDBObject {
    private String username;
    private String password;
    private Car car;
    private boolean adminRole;
    private Set<Ride> rides = new HashSet<Ride>();

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "adminRole")
    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToMany(cascade=CascadeType.ALL,mappedBy = "passengers")
    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (adminRole != user.adminRole) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (adminRole ? 1 : 0);
        return result;
    }

}
