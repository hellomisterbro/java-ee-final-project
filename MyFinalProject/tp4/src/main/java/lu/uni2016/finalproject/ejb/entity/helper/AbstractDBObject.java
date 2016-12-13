package lu.uni2016.finalproject.ejb.entity.helper;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by kirichek on 12/10/16.
 */

@MappedSuperclass
public class AbstractDBObject implements Serializable {
    private Long id;
    //used for optimistic lock exceptions
    private Date jpaVersion;
    //never delete a record, just 'flag' it
    private Date deleted;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "jpaVersion")
    @Version
    public Date getJpaVersion() {
        return jpaVersion;
    }

    public void setJpaVersion(Date jpaVersion) {
        this.jpaVersion = jpaVersion;
    }

    @Column(name = "deleted")
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @Transient
    @XmlTransient
    public Object getMyClone() {
        try {
            Class thisClass = this.getClass();
            BeanInfo bi = Introspector.getBeanInfo(thisClass);
            Object cloneObj = thisClass.newInstance();
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Method readMethod = pd.getReadMethod();
                Method writeMethod = pd.getWriteMethod();
                if (readMethod != null && writeMethod != null) {
                    Object value = readMethod.invoke(this);
                    writeMethod.invoke(cloneObj, value);
                }
            }
            return cloneObj;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDBObject)) return false;

        AbstractDBObject that = (AbstractDBObject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (jpaVersion != null ? !jpaVersion.equals(that.jpaVersion) : that.jpaVersion != null) return false;
        return deleted != null ? deleted.equals(that.deleted) : that.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jpaVersion != null ? jpaVersion.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }
}
