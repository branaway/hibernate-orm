package org.jboss.envers.test.entities.manytomany;

import org.jboss.envers.Versioned;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Many-to-many not-owning entity
 * @author Adam Warski (adam at warski dot org)
 */
@Entity
public class SetOwnedEntity {
    @Id
    private Integer id;

    @Versioned
    private String data;

    @Versioned
    @ManyToMany(mappedBy="references")
    private Set<SetOwningEntity> referencing;

    public SetOwnedEntity() {
    }

    public SetOwnedEntity(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public SetOwnedEntity(String data) {
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

    public Set<SetOwningEntity> getReferencing() {
        return referencing;
    }

    public void setReferencing(Set<SetOwningEntity> referencing) {
        this.referencing = referencing;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetOwnedEntity)) return false;

        SetOwnedEntity that = (SetOwnedEntity) o;

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
        return "SetOwnedEntity(id = " + id + ", data = " + data + ")";
    }
}