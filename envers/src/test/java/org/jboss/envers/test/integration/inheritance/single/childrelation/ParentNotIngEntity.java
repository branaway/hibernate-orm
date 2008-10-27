package org.jboss.envers.test.integration.inheritance.single.childrelation;

import org.jboss.envers.Versioned;

import javax.persistence.*;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
@Versioned
public class ParentNotIngEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    private String data;

    public ParentNotIngEntity() {
    }

    public ParentNotIngEntity(String data) {
        this.data = data;
    }

    public ParentNotIngEntity(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentNotIngEntity)) return false;

        ParentNotIngEntity that = (ParentNotIngEntity) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (id != null ? id.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "ParentNotIngEntity(id = " + getId() + ", data = " + getData() + ")";
    }
}