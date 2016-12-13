package lu.uni2016.finalproject.ejb.entity;

import lu.uni2016.finalproject.ejb.entity.helper.AbstractDBObject;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by kirichek on 12/10/16.
 */

@Entity
public class Car extends AbstractDBObject{
    private String name;
    private Integer places;

    @Column(name = "carname")
    public String getCarname() { return name; }

    public void setCarname(String name) {
        this.name = name;
    }

    @Column(name = "places")
    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        Car user = (Car) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return places != null ? places.equals(user.places) : user.places == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (places != null ? places.hashCode() : 0);
        return result;
    }
}
